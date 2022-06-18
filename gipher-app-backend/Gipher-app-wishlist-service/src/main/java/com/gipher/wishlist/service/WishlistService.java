package com.gipher.wishlist.service;
import java.util.List;

import com.gipher.wishlist.errorhandler.GifAlreadyExistsByGivenUserException;
import com.gipher.wishlist.errorhandler.GifNotFoundForGivenUserException;
import com.gipher.wishlist.model.Wishlist;

public interface WishlistService {
	
	Wishlist createWishlist(Wishlist wishlist) throws GifAlreadyExistsByGivenUserException;
	List<Wishlist> getWishlistByUserId(String userId) throws GifNotFoundForGivenUserException;
	long deleteWishItemByUserIdAndGifId(String userId,String gifId)throws GifNotFoundForGivenUserException;

}
