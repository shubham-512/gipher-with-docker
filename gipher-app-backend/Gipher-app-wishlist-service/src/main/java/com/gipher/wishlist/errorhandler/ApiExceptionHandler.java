package com.gipher.wishlist.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(GifAlreadyExistsByGivenUserException.class)
	public ResponseEntity<String> gifAlreadyExistsByGivenUserHandler(GifAlreadyExistsByGivenUserException gifAlreadyExistsByGivenUserException){
		 return new ResponseEntity<>("Gif already exists by given user", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(GifNotFoundForGivenUserException.class)
	public ResponseEntity<String> gifNotFoundForGivenUserHandler(GifNotFoundForGivenUserException gifNotFoundForGivenUserException){
		 return new ResponseEntity<>("Gif not found for given user", HttpStatus.NOT_FOUND);
	}

}
