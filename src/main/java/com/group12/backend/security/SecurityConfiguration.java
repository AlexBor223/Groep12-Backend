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

/**
 * The security configuration is where all the permitted users for routes are decided.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor with parameters
     * @param userDetailsService
     * @param bCryptPasswordEncoder
     */
    public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * configures the user using a passwordencoder
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * configures all the URL's to have permissions
     * the standard is for all requests to be authenticated
     * for certain URL's it has been decided that all users are
     * permitted.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/abbreviations").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/abbreviations/**").permitAll();

        http.authorizeRequests().antMatchers(GET, "/api/departments").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/departments/**").permitAll();

        http.authorizeRequests().antMatchers(GET, "/api/users").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/user/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/role/**").hasAnyAuthority("ROLE_ADMIN");


        http.authorizeRequests().antMatchers(PUT, "/api/abbreviations/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/api/abbreviations/**").hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers(GET, "/api/departments").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/departments").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/abbreviations").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/abbreviations/**/GiveLike").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/abbreviations/**/GiveDisLike").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/departments").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/highscores").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/highscores").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Creates an AuthenticationManager bean.
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
