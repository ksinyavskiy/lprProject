package com.training.lprProject.config.security;

import com.training.lprProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     UserService userService,
                                     AccessDeniedHandler accessDeniedHandler,
                                     AuthenticationEntryPoint authenticationEntryPoint) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                    .antMatchers("/", "index", "/css/*", "/js/*")
                        .permitAll()
                    .antMatchers(HttpMethod.POST, "/management/api/v1/students/**")
                        .hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/management/api/v1/students/**")
                        .hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/management/api/v1/students/{userId}/**")
                        .access("hasRole('ADMIN') OR authentication.principal.username == #userId")
                    .antMatchers(HttpMethod.GET, "/management/api/v1/students/**")
                        .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}