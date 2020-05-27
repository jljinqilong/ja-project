package com.champlink.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import com.champlink.gateway.filter.AuthFilter;
import com.champlink.gateway.filter.ErrorFilter;
import com.champlink.gateway.filter.LoginFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableFeignClients
@EnableZuulProxy
@ComponentScans({ @ComponentScan("com.champlink.common.util.cache") })
public class JaGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaGatewayApplication.class, args);
	}

	@Bean
	public LoginFilter getLoginFilter() {
		return new LoginFilter();
	}

	@Bean
	public ErrorFilter getErrorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public AuthFilter getAuthFilter() {
		return new AuthFilter();
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
