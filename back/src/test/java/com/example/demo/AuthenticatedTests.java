package com.example.demo;


import com.central.controller.AuthRestAPIs;
import com.central.controller.ErroController;
import com.central.controller.UsuarioController;
import com.central.entity.Role;
import com.central.entity.Usuario;
import com.central.message.response.JwtResponse;
import com.central.repository.ErroRepository;
import com.central.repository.RoleRepository;
import com.central.repository.UsuarioRepository;
import com.central.security.WebSecurityConfig;
import com.central.security.jwt.JwtAuthTokenFilter;
import com.central.security.jwt.JwtProvider;
import com.central.service.ErroService;
import com.central.service.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.central.entity.RoleName.ROLE_USER;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;


@SpringBootTest(classes = {UsuarioController.class, ErroController.class, AuthRestAPIs.class})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
public class AuthenticatedTests {
	

	@Autowired
	private MockMvc mockMvc;

	@MockBean 
    private UsuarioService service;
	
	@MockBean 
    private UsuarioRepository usuarioRepository;
	
	@MockBean 
    private RoleRepository roleRepository;

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@MockBean
	private JwtProvider jwtProvider;

	@MockBean
	private DispatcherServlet dispatcherServlet;
	
	@MockBean
	private Authentication authentication;
	
	
	@MockBean 
    private ErroService erroservice;
	
	@MockBean 
    private ErroRepository erroRepository;
	
	
	
	
	
	public static class TokenAuthenticationService {

	    static final long EXPIRATIONTIME = 864_000_000; // 10 days
	    static final String SECRET = "ThisIsASecret";
	    static final String TOKEN_PREFIX = "Bearer";
	    static final String HEADER_STRING = "Authorization";

	    public static void addAuthentication(HttpServletResponse res, String username, String password) {

	        String jwt = createToken(username, password);

	        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
	    }

	    public static Authentication getAuthentication(HttpServletRequest request) {
	        String token = request.getHeader(HEADER_STRING);
	        if (token != null) {
	            // parse the token.
	            String user = Jwts.parser()
	                    .setSigningKey(SECRET)
	                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
	                    .getBody()
	                    .getSubject();

	            return user != null ?
	                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :
	                        null;
	        }
	        return null;
	    }

	    public static String createToken(String username, String password) {
	        String jwt = Jwts.builder()
	                .setSubject(username)
	                .setSubject(password)
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	                .signWith(SignatureAlgorithm.HS512, SECRET)
	                .compact();

	        return jwt;
	    }
	}
	
	
	@Test
    public void shouldAllowUsuariosAccessToAuthenticatedUsers() throws Exception {
        String token = TokenAuthenticationService.createToken("nath", "123456789");
                
        assertNotNull(token);		
		 
		mockMvc
		.perform(
			MockMvcRequestBuilders
				.get("/usuario/")
				.header("Authentication", token)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)				
		).andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        
        
    }
	
	@Test
    public void shouldAllowErrosAccessToAuthenticatedUsers() throws Exception {
        String token = TokenAuthenticationService.createToken("nath", "123456789");
                
        assertNotNull(token);		
		 
		mockMvc
		.perform(
			MockMvcRequestBuilders
				.get("/erro/")
				.header("Authentication", token)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)				
		).andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
        
        
    }
	
	
		
	
	

	
	
	



}