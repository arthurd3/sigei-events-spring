package com.arthur.sigeievents.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests( (auth) ->{
            auth.requestMatchers("/home", "/register", "/registerUser","/login","/" , "/api/event/findJoinedUserEvents/{id}" ).permitAll()
                    .requestMatchers("/css/**","/img/**", "/js/**").permitAll()
                    .requestMatchers("/edit-user/{id}" , "/editUser/{id}" , "/admin/showUsers" , "/admin/showEvents").hasRole("ADMIN")
                    .requestMatchers("/registerEvent" , "/generate-event-pdf/{id}" , "/editEvent/{id}" , "/api/**").hasAnyRole("ORGANIZER" , "ADMIN")
                    .anyRequest().authenticated();
        } ).formLogin( form ->{
            form.loginPage("/login")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/" , true)
                    .failureUrl("/login?error=true").permitAll();
        }).logout( logout->{
            logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true).permitAll();
        }).exceptionHandling(e -> {
            e.accessDeniedPage("/");
        });
        return http.build();
    }




}
