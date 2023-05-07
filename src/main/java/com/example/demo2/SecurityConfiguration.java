package com.example.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("visual","/edit_question","/edit_survey","new_formQuestion", "new_question", "new_survey", "index").hasRole("ADMIN")
                        .requestMatchers("/index2", "author", "quiz").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('USER')"))
                        .anyRequest().authenticated()

                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);


        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user1")
                        .password("1")
                        .roles("USER")
                        .build();
        UserDetails user2 =
                User.withDefaultPasswordEncoder()
                        .username("admin2")
                        .password("2")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user,user2);
    }


}