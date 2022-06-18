package com.gipherapp.user.service;

import com.gipherapp.user.util.exception.PasswordDoesNotMatchException;
import com.gipherapp.user.util.exception.UserAlreadyExistsException;
import com.gipherapp.user.util.exception.UserNotFoundException;
import com.gipherapp.user.model.User;

public interface UserAuthService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

    public User findByEmailAndPassword(String email, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
    
    boolean updateUserProfile(User user) throws  UserNotFoundException;
    
    User getUserDetail(String email) throws UserNotFoundException;
    
    boolean changePassword(String email ,String oldPassword,String newPassword)throws UserNotFoundException ,PasswordDoesNotMatchException;
}
