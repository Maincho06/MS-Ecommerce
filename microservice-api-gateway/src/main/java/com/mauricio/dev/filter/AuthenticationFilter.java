package com.mauricio.dev.filter;

import com.mauricio.dev.config.WebClientConfig;
import com.mauricio.dev.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                String url = "http://authentication-service/auth/validate_token?token=" + authHeader;
                WebClient webClient = webClientBuilder.build();
                //jwtUtil.validateToken(authHeader);
                return webClientBuilder.build()
                        .get()
                        .uri("http://authentication-service/auth/validate_token?token=" + authHeader)
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(response -> {
                            System.out.println("RESPONSE: " + response);
                            if (response.equals("Valid")) {
                                return chain.filter(exchange);
                            } else {
                                throw new RuntimeException("Unauthorized access to application");
                            }
                        }).onErrorResume(throwable -> {
                            System.out.println("Error occurred: " + throwable.getMessage());
                            throw new RuntimeException("Error occurred while validating token");
                        });
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {}

}
