package com.gipher.app.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                
                .route("auth-service", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://AUTH-SERVICE"))
               
                .route("gipher-app-wishlist-service", r -> r.path("/api/v1/wishlist/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://GIPHER-APP-WISHLIST-SERVICE"))
                .build();
    }

}
