package com.example.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloudinary.Cloudinary;
import com.example.Service.jwtservcei.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, UserDetailsServiceImpl userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
    
    
    @Bean
	public SecurityFilterChain securityFilterChnain(HttpSecurity httpSecurity)throws Exception {
		httpSecurity
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .authorizeHttpRequests(request -> request
	        		.requestMatchers("/api/product/delete/*","/api/contectUs/**","/api/order/change/**","/api/order/all/getOrders","/api/rivew/*","/api/product/**","/api/product/add","/api/product/getProducts","/api/product/getById/*","/api/product/getByName/*","/user/auth/register","/user/auth/login","/user/auth/forgot-password","/user/auth/verify-otp","/user/auth/reset-password","/user/auth/forgot-username","/api/category/get","/api/category/name").permitAll()
	        		.anyRequest().authenticated()
	        ).addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
	}
	

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
    
    @Bean
	public Cloudinary cloudinary() {
		
		Map config=new HashMap();
		config.put("cloud_name", "dupbmhny6");
		config.put("api_key", "981621126928713");
		config.put("api_secret", "3AY4AVBQV5gJT4FwkIjCVbrzEQc");
		config.put("secure", true);
		return new Cloudinary(config);
	}
    
    @Bean
	public AuthenticationProvider authenticationProvider (UserDetailsService userdeitail) {
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider(userdeitail);
		
		provider.setPasswordEncoder( new BCryptPasswordEncoder());
		return provider;
		
	}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}