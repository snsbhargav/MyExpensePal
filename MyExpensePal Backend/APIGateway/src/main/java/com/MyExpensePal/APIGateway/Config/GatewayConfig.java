package com.MyExpensePal.APIGateway.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayConfig {
	
//	final private JwtValidationFilter jwtValidationFilter;
//	
//	public GatewayConfig(JwtValidationFilter jwtValidationFilter) {
//		this.jwtValidationFilter = jwtValidationFilter;
//	}

	@Bean
	RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("My-Expense-Pal", r -> r.path("/expense/**")
//						.filters(f-> f.filter(jwtValidationFilter))
						.uri("lb://MY-EXPENSE-PAL"))
				.route("Authentication-Service", r->r.path("/auth/**")
//						.filters(f->f.filter(new JwtValidationFilter()))
						.uri("lb://AUTHENTICATION-SERVICE"))
				.build();
	}

//	@Bean
//	@LoadBalanced
//	WebClient webClient(WebClient.Builder builder) {
//		return builder.build();
//	}
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
