package com.dh.vestidobank.security;

import org.springframework.context.annotation.Bean;
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
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		 .antMatchers("/", "/csrf", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
		 .antMatchers(HttpMethod.POST, "/api/clientes").permitAll()
		 .antMatchers(HttpMethod.GET, "/api/clientes").hasRole(RoleEnum.GERENTE.getName())
		 .antMatchers(HttpMethod.POST, "/api/gerentes").permitAll()
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
