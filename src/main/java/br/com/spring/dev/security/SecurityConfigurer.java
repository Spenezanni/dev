package br.com.spring.dev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import br.com.spring.dev.service.CustomUserDetailService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
		.antMatchers("/h2/**").permitAll()
		.antMatchers("/login/**").permitAll();
		//.antMatchers("/*/writer/**").hasRole("ADMIN");
		//.anyRequest()
		//.authenticated().and().csrf().disable()
		//.and()
		//.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		 http.headers().frameOptions().sameOrigin();
	} 

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/*
	 * public void configureGlobal(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.inMemoryAuthentication().withUser("Luiz").password("123").roles("USER").
	 * and() .withUser("Land") .password("123").roles("USER", "ADMIN"); }
	 */
}
