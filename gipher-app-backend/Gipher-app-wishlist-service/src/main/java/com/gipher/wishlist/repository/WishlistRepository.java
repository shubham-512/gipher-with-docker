package com.gipher.wishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gipher.wishlist.model.Wishlist;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist,Integer>{
	List<Wishlist> findAllByUserIdOrderByCreatedDateDesc(String userId);
	
	Optional<Wishlist> findWishlistByUserIdAndGifId(String userId, String gifId);
	
	long deleteByUserIdAndGifId(String userId,String gifId);

}
