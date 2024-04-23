package com.SE2024.SocialBookStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.SE2024.SocialBookStore.service.UserServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomSecuritySuccessHandler customSecuritySuccessHandler;
    
	@Bean 
	public UserDetailsService userDetailsService() { 
		 return new UserServiceImpl(); 
	}
	 
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); 
	}
    
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
                http.authorizeHttpRequests(
                		(authz) -> authz
                		.requestMatchers("/", "/login", "/register_admin", "/register_admin_submit", "/register_user", "/register_user_submit").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/app/**").hasAnyAuthority("USER")
                        .anyRequest().authenticated()
                		);
                
                http.formLogin(fL -> fL.loginPage("/login")
                		.failureUrl("/login?error=true")
                        .successHandler(customSecuritySuccessHandler)
                        .usernameParameter("username")
                        .passwordParameter("password"));
                
                http.logout(logOut -> logOut.logoutUrl("/logout")
                		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                		.logoutSuccessUrl("/")
                		);

                http.authenticationProvider(authenticationProvider());

                return http.build();
    }


}
