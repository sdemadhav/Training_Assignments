package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import jakarta.ws.rs.HttpMethod;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
		public RouteLocator myCustomRouterLocator(RouteLocatorBuilder builder) {
		//We can use the below array to specify all methods we want to route 
		//String [] methods= {"GET"};
		return builder
					.routes()
					.route(r->r.path("/catalog/**")
					.and().method("GET")
					.uri("http://localhost:8989"))
					.build();
		}


}
