package com.central;


import com.central.controller.AuthRestAPIs;
import com.central.entity.Role;
import com.central.entity.Usuario;
import com.central.repository.RoleRepository;
import com.central.repository.UsuarioRepository;
import com.central.security.jwt.JwtProvider;
import com.central.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.central.entity.RoleName.ROLE_USER;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;


@SpringBootTest(classes = AuthRestAPIs.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
class AuthRestAPIsTests {
	
		//https://dzone.com/articles/rest-endpoint-testing-with-mockmvc
		
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
		
			    
		@Test
		void signUpWhenCorrectData_thenReturnsOkMessage() throws Exception {
			String contentInput = "{\"name\": \"paulinha\",\"username\": \"paulinha\",\"email\": \"paulinha@oi.com\",\"role\": [\"user\"],\"password\": \"123456789\"}";

			Role role = new Role(ROLE_USER);
			when(roleRepository.findByName(ROLE_USER)).thenReturn(java.util.Optional.of(role));

			mockMvc
				.perform(
					MockMvcRequestBuilders
						.post("/api/auth/signup")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(contentInput)
				).andExpect(status().isOk())
				.andExpect(content().string("User registered successfully!"));
		}

	@Test
	void singUpWhenUsernameIsTaken_thenReturnsBadRequestMessage() throws Exception {
		String contentInput = "{\"name\": \"paulinha\",\"username\": \"paulinha\",\"email\": \"paulinha@oi.com\",\"role\": [\"user\"],\"password\": \"123456789\"}";

		Role role = new Role(ROLE_USER);
		when(roleRepository.findByName(ROLE_USER)).thenReturn(java.util.Optional.of(role));

		when(usuarioRepository.existsByUsername("paulinha")).thenReturn(true);

		mockMvc
			.perform(
				MockMvcRequestBuilders
					.post("/api/auth/signup")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(contentInput)
			).andExpect(status().isBadRequest())
			.andExpect(content().string("Fail -> Username is already taken!"));
	}

	@Test
	void signUpWhenEmailIsInUse_thenReturnsBadRequestMessage() throws Exception {
		String contentInput = "{\"name\": \"paulinha\",\"username\": \"paulinha\",\"email\": \"paulinha@oi.com\",\"role\": [\"user\"],\"password\": \"123456789\"}";

		Role role = new Role(ROLE_USER);
		when(roleRepository.findByName(ROLE_USER)).thenReturn(java.util.Optional.of(role));

		when(usuarioRepository.existsByEmail("paulinha@oi.com")).thenReturn(true);

		mockMvc
			.perform(
				MockMvcRequestBuilders
					.post("/api/auth/signup")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(contentInput)
			).andExpect(status().isBadRequest())
			.andExpect(content().string("Fail -> Email is already in use!"));
	}

	@Test
	void signUpWhenRoleIsUnknown_thenReturnsBadRequestMessage() throws Exception {

		String contentInput = "{\"name\": \"paulinha\",\"username\": \"paulinha\",\"email\": \"paulinha@oi.com\",\"role\": [\"blablacar\"],\"password\": \"123456789\"}";
		
		mockMvc
			.perform(
				MockMvcRequestBuilders
					.post("/api/auth/signup")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(contentInput)
			).andExpect(status().isBadRequest())
			.andExpect(content().string("Fail! -> Cause: User Role not found."));
	}
	
	@Test
	void signInWhenCorrectedData_thenReturnsToken() throws Exception {		
		
        Usuario paula = new Usuario();
        paula.setUsername("paulinha");
        paula.setPassword("123456789");
		List<Usuario> allUsers = Arrays.asList(paula);
        given(usuarioRepository.findAll()).willReturn(allUsers);
		
		String contentInput = "{\"username\": \"paulinha\",\"password\": \"123456789\"}";
		
		when(jwtProvider.generateJwtToken(any())).thenReturn("Token!");

		mockMvc
			.perform(
				MockMvcRequestBuilders
					.post("/api/auth/signin")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(contentInput)
			).andExpect(status().isOk())
			.andExpect(jsonPath("$.tokenType", is("Bearer")))
			.andExpect(jsonPath("$.accessToken", is("Token!")));
	}
	
	
	@Test
	void signInWhenIncorrectPassword_thenReturnsBadRequest() throws Exception {		
        String contentInput = "{\"username\": \"paulinha\", \"password\": \"aasd\"}";
        Usuario paula = new Usuario();
        paula.setUsername("paulinha");
        paula.setPassword("123456789");

        List<Usuario> allUsers = Arrays.asList(paula);

        given(usuarioRepository.findAll()).willReturn(allUsers);

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/api/auth/signin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(contentInput)
            ).andExpect(status().isBadRequest())
            .andExpect(content().string(""));
    }
	
//	@Test
//	void signInWhenUserNotFound_thenReturnsBadRequest() throws Exception {		
//		
//        String contentInput = "{\"username\": \"abcdefg\", \"password\": \"123456789\"}";
//        Usuario paula = new Usuario();
//        paula.setUsername("paulinha");
//        paula.setPassword("123456789");
//
//        List<Usuario> allUsers = Arrays.asList(paula);
//        given(usuarioRepository.findAll()).willReturn(allUsers);
//
//        mockMvc
//            .perform(
//                MockMvcRequestBuilders
//                    .post("/api/auth/signin")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON)
//                    .content(contentInput)
//            ).andExpect(status().isBadRequest())
//            .andExpect(content().string(""));
//    }
}