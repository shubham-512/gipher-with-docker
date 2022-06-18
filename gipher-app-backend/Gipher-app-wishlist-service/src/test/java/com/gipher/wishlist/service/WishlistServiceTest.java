package com.gipher.wishlist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gipher.wishlist.errorhandler.GifAlreadyExistsByGivenUserException;
import com.gipher.wishlist.errorhandler.GifNotFoundForGivenUserException;
import com.gipher.wishlist.model.Wishlist;
import com.gipher.wishlist.repository.WishlistRepository;

public class WishlistServiceTest {
	
	@Mock
	private WishlistRepository wishlistRepository;
	
	@InjectMocks
	private WishlistServiceImpl wishlistService;
	
	
	
	private Wishlist wishlist;
	private List<Wishlist> wlist,wlist2;
	private Optional<Wishlist> optionalWishlist;
	private Optional<Wishlist> optional;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
        
         wishlist = new Wishlist("1", "shubham@gmail.com", "abc123", "friends", "Shubham", "abc", LocalDate.now());
         wlist = new ArrayList<Wishlist>();
         wlist2 = new ArrayList<Wishlist>();
         wlist.add(wishlist);
         optionalWishlist = Optional.of(wishlist);
         optional = Optional.empty();
        		
	}
	
	@Test
	public void testcreateWishlistSuccess() throws GifAlreadyExistsByGivenUserException {
		Mockito.when(wishlistRepository.findWishlistByUserIdAndGifId(wishlist.getUserId(), wishlist.getGifId()))
			.thenReturn(optional);
		Mockito.when(wishlistRepository.save(wishlist))
			.thenReturn(wishlist);
		Wishlist newWishlist = wishlistService.createWishlist(wishlist);
		assertEquals(newWishlist,wishlist);
	}
	
	@Test
	public void testcreateWishlistFailure()  {
		Mockito.when(wishlistRepository.findWishlistByUserIdAndGifId(wishlist.getUserId(), wishlist.getGifId()))
			.thenReturn(optionalWishlist);
		Mockito.when(wishlistRepository.save(wishlist))
			.thenReturn(wishlist);
	
		assertThrows(GifAlreadyExistsByGivenUserException.class,
				() ->{wishlistService.createWishlist(wishlist);});
	}
	
	@Test
	public void testgetWishlistByUserIdSuccess() throws  GifNotFoundForGivenUserException {
		Mockito.when(wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(wishlist.getUserId()))
			.thenReturn(wlist);
		
		 List<Wishlist> newWishlist = wishlistService.getWishlistByUserId(wishlist.getUserId());
		assertEquals(newWishlist,wlist);
	}
	
	@Test
	public void testgetWishlistByUserIdFailure()  {
		Mockito.when(wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(wishlist.getUserId()))
			.thenReturn(wlist2);
		assertThrows(GifNotFoundForGivenUserException.class,
				() ->{wishlistService.getWishlistByUserId(wishlist.getUserId());});
		
	}
	
	@Test
	public void testdeleteWishItemByUserIdAndGifIdSuccess() throws GifNotFoundForGivenUserException {
		
		Mockito.when(wishlistRepository.deleteByUserIdAndGifId(wishlist.getUserId(), wishlist.getGifId()))
		.thenReturn(1l);
		assertEquals(wishlistService.deleteWishItemByUserIdAndGifId(wishlist.getUserId(),wishlist.getGifId()),1l);
		
	}
	
	@Test
	public void testdeleteWishItemByUserIdAndGifIdFailure()  {
		
		Mockito.when(wishlistRepository.deleteByUserIdAndGifId(wishlist.getUserId(), wishlist.getGifId()))
		.thenReturn(0l);
		assertThrows(GifNotFoundForGivenUserException.class,
				() ->{wishlistService.deleteWishItemByUserIdAndGifId(wishlist.getUserId(),wishlist.getGifId());});
		
		
	}
	
	

}
