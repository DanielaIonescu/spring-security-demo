package com.example.securingweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Slf4j
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("Daniela")
//                        .password("devSchool")
//                        .roles("USER")
//                        .build();
//
//        UserDetails admin_user =
//                User.withDefaultPasswordEncoder()
//                        .username("Admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, admin_user);
//    }

    private static final String DANIELA_ENCODED_PASSWORD = "$2a$10$/EmjNC0PMycxNpO3Ui0sK.OxFEY1hQN/0tfYzzeG9FBOyR03ZmksG"; //devSchool
    private static final String ADMIN_ENCODED_PASSWORD = "$2a$10$kzT0tJnkBrYmC3t8IikmEOXHlYJk5cKDofCpK3nzGFI0WDVXjWX7C"; //admin

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("Daniela").password(DANIELA_ENCODED_PASSWORD).roles("USER")
                .and()
                .withUser("Admin").password(ADMIN_ENCODED_PASSWORD).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/console").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        BCryptPasswordEncoder s = new BCryptPasswordEncoder();
//        log.info("Encrypted password for Daniela: {}", s.encode("devSchool"));
//        log.info("Encrypted password for Admin: {}", s.encode("admin"));
//        return s;
    }
}
