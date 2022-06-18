package com.gipherapp.user.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gipherapp.user.model.User;
import com.gipherapp.user.service.UserAuthServiceImpl;

import com.gipherapp.user.util.exception.UserNotFoundException;





@WebMvcTest(UserAuthController.class)
public class UserAuthControllerTest {

	@Autowired
	private  MockMvc mockMvc;

	@MockBean
	private  UserAuthServiceImpl userServiceImpl;
	
	 @InjectMocks
	 private UserAuthController userAuthController;
	
	private User user1;
//
//	private transient Boolean user;
//	private transient ResponseEntity<User> user2;
//	private transient User user1;




	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userAuthController).build();
		
		user1 = new User("ritika@gmail.com", "ritika123", "Up", "7895674532","Ritika");
	}

	@Test
	public void testRegisterUser() throws Exception {
		when(userServiceImpl.saveUser(user1)).thenReturn(true);
		when(userServiceImpl.findByEmailAndPassword(user1.getEmail(),user1.getPassword())).thenReturn(null);
		mockMvc.perform(post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user1))).andExpect(status()
						.isCreated()).andDo(print());
		
	}
	
	
	
	@Test
	public void testRegisterUserWhenUserExists() throws Exception {
		
		when(userServiceImpl.findByEmailAndPassword(user1.getEmail(),user1.getPassword())).thenReturn(user1);
		mockMvc.perform(post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user1))).andExpect(status()
						.isConflict()).andDo(print());
		
	}

	@Test
	public void testLoginUser() throws Exception {
		when(userServiceImpl.findByEmailAndPassword(user1.getEmail(),user1.getPassword())).thenReturn(user1);
		

		mockMvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user1))).andExpect(status().isOk()).andDo(print());


	}
	
	@Test
	public void testLoginUserwithInvalidDetails() throws Exception {
		when(userServiceImpl.findByEmailAndPassword(user1.getEmail(),user1.getPassword())).thenReturn(null);
		

		mockMvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user1))).andExpect(status().isUnauthorized()).andDo(print());
	
	}
	
	@Test
	public void testUpdateUserProfile() throws Exception{
			when(userServiceImpl.updateUserProfile(user1)).thenReturn(true);
			
			mockMvc.perform(put("/api/v1/auth/updateprofile").contentType(MediaType.APPLICATION_JSON)
					.content(jsonToString(user1))).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testChangePassword() throws Exception{
		when(userServiceImpl.changePassword(user1.getEmail(),user1.getPassword(),"password")).thenReturn(true);
		
		mockMvc.perform(put("/api/v1/auth/changepassword").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user1))).andExpect(status().isOk()).andDo(print());
	}

	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}
}
