package com.gipher.wishlist.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.gipher.wishlist.errorhandler.GifAlreadyExistsByGivenUserException;
import com.gipher.wishlist.model.Wishlist;
import com.gipher.wishlist.service.WishlistServiceImpl;





@WebMvcTest(WishlistController.class)
public class WishListControllerTest {
	
	@Autowired
	private  MockMvc mockMvc;
	
	@MockBean
	private WishlistServiceImpl wishlistService;
	
	@InjectMocks
	 private WishlistController wishlistController;
	
	private Wishlist wishlist;
	private List<Wishlist> wlist;
	
	ObjectMapper mapper = new ObjectMapper();
	

	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wishlistController).build();
        
        mapper.findAndRegisterModules();

        
         wishlist = new Wishlist("1", "shubham@gmail.com", "abc123", "friends", "Shubham", "abc", LocalDate.now());
         wlist = new ArrayList<Wishlist>();
         wlist.add(wishlist);
         
        
			
	}
	
	@Test
	public void testAddWishListSuccess()throws Exception {
		when(wishlistService.createWishlist(wishlist)).thenReturn(wishlist);
		mockMvc.perform(post("/api/v1/wishlist").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(wishlist))).andExpect(status()
						.isCreated()).andDo(print());
		
	}
	
	
	@Test
	public void testgetWishlistByUserIdSuccess()throws Exception {
		when(wishlistService.getWishlistByUserId(wishlist.getUserId())).thenReturn(wlist);
		mockMvc.perform(get("/api/v1/wishlist/shubham@gmail.com").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(wishlist))).andExpect(status()
						.isOk()).andDo(print());
		
	}
	
	@Test
	public void testdeleteWishItemByUserIdAndGifIdSuccess()throws Exception {
		when(wishlistService.deleteWishItemByUserIdAndGifId(wishlist.getUserId(),wishlist.getGifId())).thenReturn(1l);
		mockMvc.perform(get("/api/v1/wishlist/shubham@gmail.com").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(wishlist))).andExpect(status()
						.isOk()).andDo(print());
		
	}
	
	
	
	

}
