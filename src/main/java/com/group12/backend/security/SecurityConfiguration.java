package com.group12.backend.security;

import com.group12.backend.filter.CustomAuthenticationFilter;
import com.group12.backend.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/abbreviations").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/abbreviations/**").permitAll();
//        http.authorizeRequests().antMatchers(POST, "/api/abbreviations/DisLike").permitAll();
//        http.authorizeRequests().antMatchers(POST, "/api/abbreviations/Like").permitAll();
//
//
//        http.authorizeRequests().antMatchers(GET, "/api/users").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/user/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/role/**").hasAnyAuthority("ROLE_ADMIN");
//
//
//        http.authorizeRequests().antMatchers(PUT, "/api/abbreviations/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(DELETE, "/api/abbreviations/**").hasAnyAuthority("ROLE_ADMIN");
//
//        http.authorizeRequests().antMatchers(POST, "/api/departments").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/departments").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/abbreviations").permitAll();
////        http.authorizeRequests().anyRequest().authenticated();
//
//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
