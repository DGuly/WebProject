package ua.com.jee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.jee.service.UserDetailsServiceAdapter;

/**
 * Created by jsarafajr on 12/13/15.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDetailsServiceAdapter userDetailsServiceAdapter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/code").hasRole("PRE_AUTH_USER")
                .antMatchers("/data").hasRole("USER")
                .anyRequest().authenticated();

        http.csrf().disable();

        http.formLogin()
                .loginPage("/login")
                .permitAll()
                // always use the default success url despite if a protected page had been previously visited
                .defaultSuccessUrl("/code", true)
                .and()
                .logout()
                .permitAll();

    }

    @Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceAdapter).passwordEncoder(encoder);
    }
}
