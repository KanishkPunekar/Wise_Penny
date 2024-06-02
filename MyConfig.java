package com.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class MyConfig{
	
	@Bean
	public UserDetailsService getDetailsService()
	{
		return new UserDetailsServiceImple();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(getDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}
	//Configure method

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.authenticationProvider(authenticationProvider());
//	}
//	@Override
//	protected void configure(HttpSecurity http)throws Exception{
//		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//		.antMatchers("/user/**").hasRole("USER")
//		antMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
//	}
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{
	
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers("/admin/**").hasRole("ADMIN"))
		.authorizeHttpRequests(auth->auth.requestMatchers("/user/**").hasRole("USER"))
		.authorizeHttpRequests(auth->auth.requestMatchers("/**").permitAll())
		.csrf(AbstractHttpConfigurer::disable)
		.formLogin(form->form.loginPage("/").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/index"));
		//Customizer.withDefaults(
		
		
		
		http.authenticationProvider(authenticationProvider());
        return http.build();
    }
}
