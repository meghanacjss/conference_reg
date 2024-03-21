package com.example.conference_reg.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(HttpMethod.POST,"/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/login").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET,"/getall").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/createattendee/").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/allattendees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/get/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/event/addevent").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/event/getevents").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,"/event/updateevent").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/event/geteventbyid/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/create/").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/byRegistration/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/totalPayments/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/createall").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/getall").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/byEvent/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/byAttendee/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/get_rid/").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.DELETE,"/cancel/").hasRole("USER")
        );
        http.httpBasic(Customizer.withDefaults());
        //disable cross Site request Forgery(CSRF)
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserUserService();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
