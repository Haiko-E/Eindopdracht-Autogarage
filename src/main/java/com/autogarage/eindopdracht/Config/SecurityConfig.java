package com.autogarage.eindopdracht.Config;

import com.autogarage.eindopdracht.Model.JwtRequestFilter;
import com.autogarage.eindopdracht.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JWTService jwtService;

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from employees where username=?")
                .authoritiesByUsernameQuery("select username, role from employees where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET,"/**").authenticated()
                .antMatchers("/appointments/**").hasAnyAuthority("ADMIN", "SUPERUSER")
                .antMatchers("/cars/**").hasAnyAuthority("ADMIN", "SUPERUSER")
                .antMatchers("/car-papers/**").hasAnyAuthority("ADMIN", "SUPERUSER")
                .antMatchers("/customers/**").hasAnyAuthority("ADMIN", "SUPERUSER")
                .antMatchers("/employees/**").hasAnyAuthority("ADMIN", "SUPERUSER")
                .antMatchers("/invoices/**").hasAnyAuthority("ADMIN", "SUPERUSER")
                .antMatchers("/maintenances/**").hasAnyAuthority("MECHANIC", "SUPERUSER")
                .antMatchers("/maintenance-items/{id}/create-invoice").hasAnyAuthority("ADMIN", "SUPERUSER")
                .antMatchers("/maintenance-items/**").hasAnyAuthority("MECHANIC", "SUPERUSER")
                .antMatchers("/parts/**").hasAnyAuthority("MECHANIC", "SUPERUSER")
                .antMatchers("/repair-operations/**").hasAnyAuthority("MECHANIC", "SUPERUSER")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
