package com.figureshop.springmvc.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_REQUEST = "/login";
    private static final String[] AUTHORIZED_REQUESTS_ANYBODY = new String[]{"/","/login","/login/**", "/register/**",
                                                                "/resources/**", "/cart/**", "/product/**", "/info"};
    private static final String[] AUTHORIZED_REQUESTS_ADMIN = new String[]{"/admin"};

    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * We do the configuration of spring security here.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests() // We define the authorization here
                .antMatchers(AUTHORIZED_REQUESTS_ADMIN).hasRole("ADMIN") // For the request to "/admin", the user needs to be an admin
                .antMatchers(AUTHORIZED_REQUESTS_ANYBODY).permitAll() // For the request to the index page, any user has access
                .anyRequest().authenticated() // For all the other requests, the user needs to be authenticated
                .and()
                .formLogin() // We define the login part here.
                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())// This handler is already provided by spring to redirect to the last request
                //.failureHandler(new FailureHandl)
                .loginPage(LOGIN_REQUEST) // We specify a login page. Otherwise spring create one by default
                .permitAll() // To make the login page the available for any user
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bcryptPasswordEncoder());
    }
}