package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.central.controller.UsuarioController;
import static org.hamcrest.Matchers.*;
import com.central.entity.Erro;
import com.central.entity.Usuario;
import com.central.service.ErroService;
import com.central.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class ApplicationTests {
	//https://dzone.com/articles/rest-endpoint-testing-with-mockmvc
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean 
    private UsuarioService service;
	
		    
	@Test
	void case1() throws Exception {
		List<Usuario> userList = new ArrayList<>();
		Usuario user1 =new Usuario("nathalia", "nath", "12345", "nath@mail.com");
		Usuario user2 =new Usuario("larissa", "lari", "67890", "lari@mail.com");
		userList.add(user1);
		userList.add(user2);
		
		Mockito.when(service.getAllUsuarios()).thenReturn(userList);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/usuario/post").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].name", is("nathalia")))
			.andExpect(jsonPath("$[0].username", is("nath")))
			.andExpect(jsonPath("$[0].password", is("12345")))
			.andExpect(jsonPath("$[0].email", is("nath@mail.com")))
			.andExpect(jsonPath("$[1].name", is("larissa")))
			.andExpect(jsonPath("$[1].username", is("lari")))
			.andExpect(jsonPath("$[1].password", is("67890")))
			.andExpect(jsonPath("$[1].email", is("lari@mail.com")));
	}
		
		
		
	

}