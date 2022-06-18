package com.gipher.wishlist.errorhandler;

public class GifNotFoundForGivenUserException extends Exception{
	public GifNotFoundForGivenUserException(){
		super();
	}
	
	public GifNotFoundForGivenUserException(String message){
		super(message);
	}

}
