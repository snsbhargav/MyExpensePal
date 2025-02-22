package com.MyExpensePal.APIGateway.Config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

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
		ResponseEntity<Boolean> isTokenValid = new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
		
		//Leaving Authentication service API's open
		if (exchange.getRequest().getURI().getPath().startsWith("/auth/")) {
			return chain.filter(exchange);
		} else if (token == null)
			return createUnauthorizedException(exchange, "Invalid or Bearer token Missing");

		
		token = token.substring(7);
		try {
			isTokenValid = restTemplate.postForEntity("lb://AUTHENTICATION-SERVICE/auth/validateToken?token=" + token,
					null, Boolean.class);

			if (!isTokenValid.getBody()) {
				return createUnauthorizedException(exchange, "Invalid Token");
			} else
				return chain.filter(exchange);
		} catch (HttpClientErrorException.Unauthorized e) {
			return createUnauthorizedException(exchange, "Invalid Token");
		} catch (Exception e) {
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
	//We have to do it manually because RestCOntrollerAdvice don't work here because we are still at filters not yet entered into Controller.
	private Mono<Void> createUnauthorizedException(ServerWebExchange exchange, String message) {
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		byte[] responseByte = ("{\"message\" : " + message + ", \"Status\" : 401}").getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(responseByte);
		return exchange.getResponse().writeWith(Mono.just(buffer));

	}

}
