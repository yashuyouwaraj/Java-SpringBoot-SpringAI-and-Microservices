package com.yashu.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> gatewayRoutes() {
		return route("quiz-service")
				.route(path("/QUIZ-SERVICE/**"), http())
				.before(stripPrefix(1))
				.filter(lb("quiz-service"))
				.build()
				.and(route("question-service")
						.route(path("/QUESTION-SERVICE/**"), http())
						.before(stripPrefix(1))
						.filter(lb("question-service"))
						.build());
	}

}
