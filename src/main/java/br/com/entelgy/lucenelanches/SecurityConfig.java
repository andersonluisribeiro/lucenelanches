package br.com.entelgy.lucenelanches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.entelgy.lucenelanches.repositories.UserDAO;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
		@Autowired
		private UserDAO dao;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
            .authorizeRequests()
            		.antMatchers("/orders").hasRole("ADMIN")
            		.antMatchers("/").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .formLogin()
            		.defaultSuccessUrl("/orders")
            		.and()
            .logout()
                .permitAll();
       
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/css/**");
      web
      .ignoring()
         .antMatchers("/js/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(dao).passwordEncoder(new BCryptPasswordEncoder());
    }
}