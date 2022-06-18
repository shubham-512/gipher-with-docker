package com.gipher.wishlist.errorhandler;

public class GifAlreadyExistsByGivenUserException extends Exception{
	public GifAlreadyExistsByGivenUserException() {
		super();
		
	}
	public GifAlreadyExistsByGivenUserException(String message) {
	super(message);
		
	}

}
