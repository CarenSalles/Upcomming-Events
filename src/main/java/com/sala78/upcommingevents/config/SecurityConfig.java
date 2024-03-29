package com.sala78.upcommingevents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



import com.sala78.upcommingevents.services.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private MyAuthenticationEntryPoint authenticationEntryPoint;

        private JpaUserDetailsService service;

        public SecurityConfig(JpaUserDetailsService service) {
                this.service = service;
        }

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .cors()
                                .and()
                                .headers(header -> header.frameOptions().sameOrigin())
                                .csrf(csrf -> csrf.disable())
                                .formLogin(form -> form.loginPage("/api/login")
                                .disable())
                                .logout(logout -> logout
                                                .logoutUrl("/api/logout")
                                                .deleteCookies("JSESSIONID"))
                                .authorizeRequests((auth) -> auth
                                                .antMatchers("/api/register","/images/*").permitAll()
                                                .antMatchers("/api/login").hasAnyRole("ADMIN", "USER")
                                                .antMatchers("/api/events").hasAnyRole("ADMIN", "USER")
                                                .antMatchers("/api/events/**").hasAnyRole("ADMIN", "USER")
                                                .antMatchers("/api/user","/api/event/increment").hasRole("USER")
                                                .antMatchers("/api/users/**").hasRole("USER")
                                                .antMatchers("/api/admin").hasRole("ADMIN")
                                                .antMatchers("/api/login?logout").permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .userDetailsService(service)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                                .httpBasic(basic -> basic
                                                .authenticationEntryPoint(authenticationEntryPoint));

                return http.build();

        }

        @Bean
        PasswordEncoder passwordEncoder() {
                
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

   

}
