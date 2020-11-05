package com.dh.vestidobank.resouce;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.dh.vestidobank.exception.handler.ValidationResponseError;
import com.dh.vestidobank.model.dto.create.GerenteCreateDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GerenteRestTest {

	@LocalServerPort
	private String serverPort;
	
	@Autowired
	private ObjectMapper om;
	
	@Autowired
	private MockMvc mockMvc;
	
	private final String URL = "/api/gerentes";
	
	@Test
	@WithMockUser(username="tantofaz", password = "tantofaz", authorities = "ROLE_GERENTE")
	public void testPostExpectCreated() throws JsonProcessingException, Exception {
		
		GerenteCreateDTO gerenteDto = GerenteCreateDTO.builder()
				.cpf("25578331009")
				.email("testeapi@email.com")
				.endereco("Endereco do gerente")
			    .nome("Nome do Gerente")
			    .senha("Senhasuperdificildogerente")
			    .build();
		
		
		ResultActions resultActions = this.mockMvc.perform(
				post(this.URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(this.om.writeValueAsString(gerenteDto)));
		
		resultActions
			.andExpect(status().isCreated())
			.andExpect(redirectedUrlPattern("**//**" + this.URL + "/{[0-9]*}"));
	}
	
	@Test
	@WithMockUser(username="tantofaz", password = "tantofaz")
	public void testPostExpectForbidden() throws JsonProcessingException, Exception {
		
		GerenteCreateDTO gerenteDto = GerenteCreateDTO.builder()
				.cpf("25578331009")
				.email("testeapi@email.com")
				.endereco("Endereco do gerente")
			    .nome("Nome do Gerente")
			    .senha("Senhasuperdificildogerente")
			    .build();
		
		
		ResultActions resultActions = this.mockMvc.perform(
				post(this.URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(this.om.writeValueAsString(gerenteDto)));
		
		resultActions
			.andExpect(status().isForbidden());
	}
	
	@Test
	public void testPostExpectUnauthorized() throws JsonProcessingException, Exception {
		
		GerenteCreateDTO gerenteDto = GerenteCreateDTO.builder()
				.cpf("25578331009")
				.email("testeapi@email.com")
				.endereco("Endereco do gerente")
			    .nome("Nome do Gerente")
			    .senha("Senhasuperdificildogerente")
			    .build();
		
		
		ResultActions resultActions = this.mockMvc.perform(
				post(this.URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(this.om.writeValueAsString(gerenteDto)));
		
		resultActions
			.andExpect(status().isUnauthorized());
	}
	
	
	@Test
	@WithMockUser(username="tantofaz", password = "tantofaz", authorities = "ROLE_GERENTE")
	public void testPostExpectUnprocessableByCpf() throws JsonProcessingException, Exception {
		
		GerenteCreateDTO gerenteDto = GerenteCreateDTO.builder()
				.cpf("255783310")
				.email("testeapi@emai.com")
				.endereco("Endereco do gerente")
			    .nome("Nome do Gerente")
			    .senha("Senhasuperdificildogerente")
			    .build();
		
		
		ResultActions resultActions = this.mockMvc.perform(
				post(this.URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(this.om.writeValueAsString(gerenteDto)));
		
		resultActions
		    .andDo(resultRequest -> {
		    	 ValidationResponseError errors = this.getValidationErrorOf(resultRequest);
		    	 assertTrue(errors.getErrors().size() == 1);
		    	 assertTrue(
		    			 errors.getErrors()
		    			 .stream()
		    			 .map(fieldError-> fieldError.getField())
		    			 .anyMatch(field -> "cpf".equals(field))
		    			 );
		    })
			.andExpect(status().isUnprocessableEntity());
	}
	
	
	public ValidationResponseError getValidationErrorOf(MvcResult result) throws JsonParseException, JsonMappingException, IOException {
		String jsonResponseBody = result.getResponse().getContentAsString();
		ValidationResponseError err = this.om.readValue(jsonResponseBody, ValidationResponseError.class);
		return err;
	}
	
}
