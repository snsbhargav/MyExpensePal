package com.MyExpensePal.APIGateway.Config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import jakarta.ws.rs.core.SecurityContext;
import reactor.core.publisher.Mono;

@Component
public class JwtValidationFilter implements WebFilter {

//	@Autowired
//	WebClient webClient;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		// TODO Auto-generated method stub
		String token = exchange.getRequest().getHeaders().getFirst("Authorization");

		// Leaving Authentication service API's open
		String path = exchange.getRequest().getURI().getPath();
		if (path.startsWith("/auth/login") || path.startsWith("/auth/register")
				|| exchange.getRequest().getMethod().matches("OPTIONS")) {
			return chain.filter(exchange);
		} else if (token == null)
			return createUnauthorizedException(exchange, "Invalid or Bearer token Missing");

		// Creating the header to pass it for Authentication.
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", token);
		HttpEntity<String> httpEntity = new HttpEntity<>(header);

		// Slicing out the token
		token = token.substring(7);
		final UUID userId;
		try {
			userId = restTemplate
					.postForEntity("lb://AUTHENTICATION-SERVICE/auth/validateToken", httpEntity, UUID.class).getBody();

			if (userId == null) {
				return createUnauthorizedException(exchange, "Invalid Token");
			} else {
				ServerWebExchange modifiedExchange = exchange.mutate()
						.request(request -> request.header("userId", userId.toString())).build();

				return chain.filter(modifiedExchange);
			}

		} catch (HttpClientErrorException.Unauthorized e) {
			return createUnauthorizedException(exchange, "Invalid Token");
		} catch (Exception e) {
			System.out.println(e);
			return createUnauthorizedException(exchange, "Authentication Service Unavailable.");
		}

		// TODO Use Web-Client later on
//		return webClient
//				.post()
//				.uri("lb://AUTHENTICATION-SERVICE/auth/validateToken?token="+token)
//				.retrieve()
//				.bodyToMono(Boolean.class)
//				.block();

	}

	// We have create a custom response with status code, JSON content.
	// We have to do it manually because RestCOntrollerAdvice don't work here
	// because we are still at filters not yet entered into Controller.
	private Mono<Void> createUnauthorizedException(ServerWebExchange exchange, String message) {
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		byte[] responseByte = ("{\"message\" : " + message + ", \"Status\" : 401}").getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(responseByte);
		return exchange.getResponse().writeWith(Mono.just(buffer));

	}

}
