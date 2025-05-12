package com.thekingmoss.config;
import com.thekingmoss.props.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@RequiredArgsConstructor
public class SecurityConfig {
    private final SecurityProperties properties;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if(!properties.csrf().enabled()){
            http.csrf(AbstractHttpConfigurer::disable);
        }
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "api/product").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "api/product/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "api/product/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "api/product/**").permitAll()

                .requestMatchers(HttpMethod.POST, "api/category").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "api/category/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "api/category/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/category/**").permitAll()

                .requestMatchers(HttpMethod.POST, "api/productImage").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "api/productImage/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/productImage/**").permitAll()
                .requestMatchers("api/users/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder encoder){
        List<UserDetails> users = properties.user().memory().users().stream()
                .map(um -> User.builder()
                        .username(um.name())
                        .password(encoder.encode(um.password()))
                        .roles(um.roles().toArray(String[]::new))
                        .build()).toList();
        return new InMemoryUserDetailsManager(users);
    }
}
