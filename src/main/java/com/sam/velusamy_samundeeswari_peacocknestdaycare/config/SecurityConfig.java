package com.sam.velusamy_samundeeswari_peacocknestdaycare.config;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/userRegistration","/userRegistration/save").permitAll()
                        .requestMatchers("/index","/welcome","/calculate",
                                            "/childRegister","/fullTimeService",
                                "/partTimeService","/afterSchoolService").permitAll()
                        .requestMatchers("/registeredUsers","/adminDashboard","/registeredChildren/list").hasRole( "ADMIN")
                        .requestMatchers("/parentDashboard","/childInfo").hasAnyRole("PARENT")
                        .requestMatchers("/static/css/**","/static/images/**","/static/js/**").permitAll()
                        .requestMatchers("/contact","/contact/list","/emailSubscription",
                                "/emailSubscription/list").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler())
                        .permitAll()
                ).logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            String email = authentication.getName();
            if (email.endsWith("@admin.com")) {
                response.sendRedirect("/adminDashboard");
            } else {
                response.sendRedirect("/parentDashboard");
            }
        };
    }
}