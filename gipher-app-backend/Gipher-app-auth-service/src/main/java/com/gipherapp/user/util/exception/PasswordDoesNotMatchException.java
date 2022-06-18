package com.gipherapp.user.util.exception;

public class PasswordDoesNotMatchException extends Exception {
	public PasswordDoesNotMatchException(){
		super();
	}
	
	public PasswordDoesNotMatchException(String message){
		super(message);
	}

}
