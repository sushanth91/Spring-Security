package com.springsecurity.config;

import com.springsecurity.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//When you write @PreAuthorize("hasAnyRoController layerle('ADMIN')") in
//
//public class MySecurityConfig extends WebSecurityConfigurerAdapter {
public class MySecurityConfig{


    @Autowired
    private CustomUserDetailService customUserDetailService;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//To generate CSRF Token
//                .and()
//                //.csrf().disable()//CSRF is Enabled for non Browser Clients.
//                //So weare disabling it. Otherwise we cannot POST and PUT data. CSRF is a ATTACK
//                .authorizeRequests()
//                .antMatchers("/signin").permitAll()
//                .antMatchers("/public/**").hasRole("NORMAL")
//                .antMatchers("/users/**").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/signin")
//                .loginProcessingUrl("/dologin")
//                .defaultSuccessUrl("/users/");
//
//
//
//        //.httpBasic();//for basic
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//To generate CSRF Token
                .and()
                //.csrf().disable()//CSRF is Enabled for non Browser Clients.
                //So weare disabling it. Otherwise we cannot POST and PUT data. CSRF is a ATTACK
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/public/**").hasRole("NORMAL")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/users/");
        http.authenticationProvider(daoAuthenticationProvider());
        DefaultSecurityFilterChain defaultSecurityFilterChain=http.build();
        return defaultSecurityFilterChain;
    }
    //ROLE - High level Overview-> NORMAL : READ
    //Authority- permission-> READ
    //ADMIN- READ,WRITE,UPDATE

//As Plain Text
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("John").password("durgesh").roles("NORMAL");
//        auth.inMemoryAuthentication().withUser("Roshni").password("durgesh").roles("ADMIN");
//    }
//@Bean
//public PasswordEncoder passwordEncoder(){
//    return NoOpPasswordEncoder.getInstance();//As Plain Text
//}

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//   auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//    }
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("John").password(this.passwordEncoder().encode("durgesh")).roles("NORMAL");
//        auth.inMemoryAuthentication().withUser("Roshni").password(this.passwordEncoder().encode("durgesh")).roles("ADMIN");
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
    provider.setUserDetailsService(this.customUserDetailService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
    }

    //    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(10);//As Plain Text
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);//As Plain Text
    }
}
//}

//CSRF