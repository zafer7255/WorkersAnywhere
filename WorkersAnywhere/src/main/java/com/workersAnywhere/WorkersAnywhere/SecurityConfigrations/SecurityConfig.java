package com.workersAnywhere.WorkersAnywhere.SecurityConfigrations;


import com.workersAnywhere.WorkersAnywhere.CustomSecurityFilters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     @Autowired
     UserDetailsService userDetailsService;
     @Autowired
     JwtFilter jwtFilter;

     @Bean
    public AuthenticationProvider authenticationProvider()
     {
         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
         provider.setUserDetailsService(userDetailsService);
         provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
         return provider;
     }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register","/register/**").permitAll() // Public endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Restrict to Admins
                        .requestMatchers("/worker/**").hasRole("WORKER") // Restrict to Workers
                        .requestMatchers("/customer/**").hasRole("CUSTOMER") // Restrict to Customers
                        .anyRequest().authenticated() // All other requests need authentication
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
         return config.getAuthenticationManager();
     }


}
