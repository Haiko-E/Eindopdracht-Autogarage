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
                .authorizeRequests().antMatchers(HttpMethod.POST, "/auth").permitAll()
               .and()
               .authorizeRequests().antMatchers("/**").hasAuthority("SUPERUSER")
               .and()
               .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/appointments").hasAnyAuthority("MECHANIC", "ADMIN")
                .antMatchers( "/appointments" ).hasAuthority("ADMIN")
                .and()
               .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/cars").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/cars").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cars").hasAuthority("ADMIN")
               .and()
               .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/customers").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/customers").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/customers").hasAuthority("ADMIN")
               .and()
               .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/employees").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/employees").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/employees").hasAuthority("ADMIN")
               .and()
               .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/maintenances").hasAuthority("MECHANIC")
                .antMatchers(HttpMethod.PUT, "/maintenances").hasAuthority("MECHANIC")
                .antMatchers(HttpMethod.DELETE, "/maintenances").hasAuthority("MECHANIC")
               .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
