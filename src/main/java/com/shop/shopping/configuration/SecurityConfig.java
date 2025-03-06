package com.shop.shopping.configuration;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.shop.shopping.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtUtil jwtRequestFilter;


	public SecurityConfig(JwtUtil jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
						.requestMatchers(AntPathRequestMatcher.antMatcher("/account")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/token")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/products/**")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/cart/**")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/envie/**")).permitAll()
						.anyRequest().authenticated())
				.headers(httpSecurityHeadersConfigurer ->
						         httpSecurityHeadersConfigurer
								         .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
								         .httpStrictTransportSecurity(hstsConfig -> hstsConfig.includeSubDomains(true).maxAgeInSeconds(15768000))
								         .referrerPolicy(referrerPolicyConfig -> referrerPolicyConfig.policy(
										         ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)))
				.sessionManagement(httpSecuritySessionManagementConfigurer ->
						                   httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);
		// @formatter:on
		return http.build();
	}

	private CorsFilter corsFilter() {
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowedHeaders(Collections.singletonList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH"));
		config.setAllowedOriginPatterns(List.of("http://localhost:8080"));
		config.setAllowCredentials(true);
		config.setMaxAge(1800L);
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Utilise BCrypt pour le hachage des mots de passe
	}
}
