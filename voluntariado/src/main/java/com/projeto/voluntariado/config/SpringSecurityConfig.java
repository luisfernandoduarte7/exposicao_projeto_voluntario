package com.projeto.voluntariado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http)throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests((requests)-> requests

				.requestMatchers(
						"/v3/api-docs/**",
						"/swagger-ui/**",
						"/swagger-ui/index.html")
				.permitAll()

				.requestMatchers(
						HttpMethod.POST,"/voluntario/", "/projeto/", "/organizador/")
				.permitAll()

				.requestMatchers(
						HttpMethod.GET,"/voluntario/","/voluntario/{id}", "/projeto/","/projeto/{id}", "/organizador/","/organizador/{id}","/voluntario/nome/{nome}","/projeto/localevento/{localevento}","/organizador/nomeong/{nomeong}")
				.permitAll()

				.requestMatchers(
						HttpMethod.DELETE,"/voluntario/{id}", "/projeto/{id}", "/organizador/{id}")
				.permitAll()

				.requestMatchers(
						HttpMethod.PUT,"/voluntario/{id}", "/projeto/{id}", "/organizador/{id}")
				.permitAll()

				.anyRequest()
				.denyAll()
				)

		.httpBasic();
		return http.build();
	}
	@Bean
	public InMemoryUserDetailsManager userDetailsSevice() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("voluntario")
				.password("ajuda")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
