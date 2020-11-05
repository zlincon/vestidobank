package com.dh.vestidobank.security;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dh.vestidobank.model.entity.RoleEnum;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityWebConfig extends WebSecurityConfigurerAdapter{
	private UserDetailsServiceImpl userService;
	
	private Environment env;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		 .antMatchers("/", "/csrf",
				 "/v2/api-docs", 
				 "/configuration/ui",
				 "/swagger-resources/**",
				"/configuration/**", 
				"/swagger-ui.html",
				"/webjars/**",
				"/h2/**",
				"/h2-console/**"
				
				 ).permitAll()
		 .antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
		 .antMatchers(HttpMethod.GET, "/api/clientes/**").permitAll()
		 .antMatchers(HttpMethod.GET, "/api/clientes").hasRole(RoleEnum.GERENTE.getName())
		 .antMatchers(HttpMethod.POST, "/api/gerentes").hasRole(RoleEnum.GERENTE.getName())
		 .anyRequest().authenticated()
		 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and().cors().disable().csrf().disable()
		 .httpBasic();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());

	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
