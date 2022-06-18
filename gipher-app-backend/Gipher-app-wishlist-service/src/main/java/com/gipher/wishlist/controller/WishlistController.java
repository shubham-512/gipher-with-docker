package com.gipher.wishlist.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gipher.wishlist.errorhandler.GifAlreadyExistsByGivenUserException;
import com.gipher.wishlist.errorhandler.GifNotFoundForGivenUserException;
import com.gipher.wishlist.model.Wishlist;
import com.gipher.wishlist.service.WishlistService;

@RestController
@RequestMapping("/api/v1/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	private Map<String, String> map = new HashMap<>();
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Wishlist addWishlist(@RequestBody Wishlist wishlist) throws GifAlreadyExistsByGivenUserException {
		return wishlistService.createWishlist(wishlist);
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{userId}")
	public List<Wishlist> getWishlistByUserId(@PathVariable String userId)throws GifNotFoundForGivenUserException{
		return wishlistService.getWishlistByUserId(userId);
	}
	
	@PostMapping("/deltewishitem")
	public ResponseEntity<?> deleteWishListByUserIdAndGifId(@RequestBody Map<String, String> data)throws GifNotFoundForGivenUserException{
		wishlistService.deleteWishItemByUserIdAndGifId(data.get("userId"), data.get("gifId"));
		map.clear();
		map.put("message", "Gif removed from wishlist");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	

}
