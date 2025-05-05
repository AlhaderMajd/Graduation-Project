//package com.example.Graduation.Project.security;
//
//import com.example.Graduation.Project.user.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final UserService userDetailsService;
//
//    public SecurityConfig(UserService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        //.requestMatchers("/api/colleges").authenticated()
//                        //.requestMatchers(HttpMethod.GET, "/api/users/{userId}").hasAnyRole("ADMIN", "AUTHOR", "MEMBER")
//                        .requestMatchers(HttpMethod.GET, "/api/colleges").hasRole("طالب")
//                        .anyRequest().denyAll()
//                )
//                .httpBasic(Customizer.withDefaults()) // Use Basic Auth instead
//                .formLogin(AbstractHttpConfigurer::disable); // Disable form login
//
//        http.authenticationProvider(authenticationProvider());
//        return http.build();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}