package com.miempresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.miempresa.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion {

	@Autowired
	private UsuarioServicio userDet;
	
	@Autowired
	private BCryptPasswordEncoder bycryp;
	
	@Bean
	public BCryptPasswordEncoder passEncoder() {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		return bcpe;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDet).passwordEncoder(bycryp);
	}
	
	@Bean
	public SecurityFilterChain secutiryFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(authz) -> authz
				.requestMatchers("/", "/listarClientes").permitAll()
				.requestMatchers("/agregarCliente").hasRole("ADMIN")
				.requestMatchers("/editarCliente").hasRole("ADMIN")
				.requestMatchers("/eliminarCliente/*").hasRole("ADMIN")
				.requestMatchers("/mostrarCliente").hasRole("ADMIN")
				.requestMatchers("/guardarCliente").hasRole("ADMIN")
				.anyRequest().authenticated())
				.csrf((csrf) -> csrf.disable())
				.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/listarClientes", true)
                .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(e -> e
                .accessDeniedPage("/error"));
		return http.build();
	}
	
}

