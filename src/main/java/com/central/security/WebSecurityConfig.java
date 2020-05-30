package com.central.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.central.security.jwt.JwtAuthEntryPoint;
import com.central.security.jwt.JwtAuthTokenFilter;
import com.central.security.services.UserDetailsServiceImpl;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableSwagger2
@Import(SecurityProblemSupport.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
 
    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;
    
  //Configuracoes de autenticacao
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
 
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
	public JwtAuthTokenFilter authenticationTokenFilterBean()
			throws Exception {
		return new JwtAuthTokenFilter();
	}
    
  //Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    		httpSecurity
    		.csrf()
    		.disable()
    		//configuração relacionada à segurança
    		.exceptionHandling()
    		.authenticationEntryPoint(unauthorizedHandler)
    		.and()
    		.sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
    		.authorizeRequests()
    		.antMatchers( "/", "/*.html", "/favicon.ico",
    				"/**/*.html", "/**/*.css", "/**/*.js").permitAll()
    		.antMatchers("/api/auth/**").permitAll()
    		.anyRequest()
    		.authenticated();
    		httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
    				UsernamePasswordAuthenticationFilter.class);
    		httpSecurity.headers().cacheControl();
	}
      	   	
  //Configuracoes de recursos estaticos(js, css, imagens, etc.)
 @Override
 public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/v2/api-docs", "/v3/api-docs", "/configuration/ui", 
    			"/swagger-resources/**", "/configuration/**", "/swagger-ui.html"
    				, "/webjars/**", "/csrf", "/");
   }
        
}
    


    
    	

		


				



	                
	        
	          
	    
	  

	
	     
    

   
    	
