package com.baimuhtar.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        builder.jdbcAuthentication().dataSource(dataSource);

        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        builder.inMemoryAuthentication()
                .withUser((userBuilder.username("mukhtar").password("123").roles("SELLER")))
                .withUser(userBuilder.username("zhandik").password("123").roles("BUYER"));
    }




    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").hasAnyRole("SELLER", "BUYER")
                .antMatchers(HttpMethod.GET, "/products").hasAnyRole("SELLER", "BUYER")
                .antMatchers(HttpMethod.GET, "/products/{id}").hasAnyRole("SELLER", "BUYER")
                .antMatchers(HttpMethod.POST, "/products").hasRole("SELLER")
                .antMatchers(HttpMethod.PUT, "/products").hasRole("SELLER")
                .antMatchers(HttpMethod.DELETE, "/products").hasRole("SELLER")
                .and().formLogin().permitAll();
    }
}
