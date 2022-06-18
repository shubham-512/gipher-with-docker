package com.gipher.wishlist.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gipher.wishlist.errorhandler.GifAlreadyExistsByGivenUserException;
import com.gipher.wishlist.errorhandler.GifNotFoundForGivenUserException;
import com.gipher.wishlist.model.Wishlist;
import com.gipher.wishlist.repository.WishlistRepository;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {
	
	@Autowired
	WishlistRepository wishlistRepository;

	@Override
	public Wishlist createWishlist(Wishlist wishlist) throws GifAlreadyExistsByGivenUserException {
		
		Optional<Wishlist> optionalWishlist = wishlistRepository.findWishlistByUserIdAndGifId(wishlist.getUserId(), wishlist.getGifId());
		if(optionalWishlist.isPresent()) {
			throw new GifAlreadyExistsByGivenUserException("Gif already exists by user");
		}
		
		wishlist.setCreatedDate(LocalDate.now());
		
		Wishlist savedWishlist = wishlistRepository.save(wishlist);
		
		return savedWishlist;
	}

	@Override
	public List<Wishlist> getWishlistByUserId(String userId) throws GifNotFoundForGivenUserException{
		List<Wishlist> wishlistItems = wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
		if(wishlistItems.isEmpty()) {
			throw new GifNotFoundForGivenUserException("gif not found for given user");
		}
		
		 return wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
	}

	@Override
	public long deleteWishItemByUserIdAndGifId(String userId, String gifId)throws GifNotFoundForGivenUserException {
		long deleteCount = wishlistRepository.deleteByUserIdAndGifId(userId, gifId);
		if(deleteCount == 0) {
			throw new GifNotFoundForGivenUserException("gif not in the wishlist");
		}
		return deleteCount;
	}

}
