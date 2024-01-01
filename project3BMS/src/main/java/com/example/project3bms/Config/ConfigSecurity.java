package com.example.project3bms.Config;

import com.example.project3bms.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/bms/customer/register","api/v1/bms/employee/register").permitAll()
                .requestMatchers("/api/v1/bms/user/get","/api/v1/bms/user/add","/api/v1/bms/user/update","/api/v1/bms/user/delete").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/bms/employee/add","/api/v1/bms/employee/get","/api/v1/bms/employee/update","/api/v1/bms/employee/delete").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/bms/customer/get-customers","/api/v1/bms/customer/add","/api/v1/bms/customer/update","/api/v1/bms/customer/delete","/api/v1/bms/account/active","/api/v1/bms/account/block/**").hasAnyAuthority("ADMIN","EMPLOYEE")
                .requestMatchers("/api/v1/bms/account/update-account/**","/api/v1/bms/account/delete-account/**","/api/v1/bms/account/withdraw/**","/api/v1/bms/account/deposit/**","/api/v1/bms/account/get-accounts").hasAuthority("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }

}
