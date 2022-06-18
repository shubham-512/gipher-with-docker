package com.gipherapp.user.service;

import com.gipherapp.user.util.exception.PasswordDoesNotMatchException;
import com.gipherapp.user.util.exception.UserAlreadyExistsException;
import com.gipherapp.user.util.exception.UserNotFoundException;
import com.gipherapp.user.model.User;
import com.gipherapp.user.repository.UserAuthRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class UserAuthServiceImpl implements UserAuthService {

	
	
	@Autowired
	private UserAuthRepository userAuthRepo;
	
	

	
    
    @Override
    public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {
    	User user = userAuthRepo.findByEmailAndPassword(email, password);
		if(user != null)
			return user;
		return null;
    }


    
    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
    	Optional<User> isUserExists = userAuthRepo.findById(user.getEmail());
    	if(isUserExists.isEmpty()) {
    		userAuthRepo.save(user);
    		return true;
    	}
    	else{
    		throw new UserAlreadyExistsException("User Already Exists.");
    	}
    }

	@Override
	public boolean updateUserProfile(User user) throws UserNotFoundException {
		Optional<User> isUserExists = userAuthRepo.findById(user.getEmail());
		if(!isUserExists.isEmpty()) {
			user.setPassword(isUserExists.get().getPassword());
    		userAuthRepo.save(user);
    		return true;
    	}
    	else{
    		throw new UserNotFoundException("User Not found.");
    	}
		
	}



	@Override
	public User getUserDetail(String email) throws UserNotFoundException {
		Optional<User> isUserExists = userAuthRepo.findById(email);
		if(!isUserExists.isEmpty()) {
			
    		User user = userAuthRepo.findByEmailAndPassword(email, isUserExists.get().getPassword());
    		
    		return isUserExists.get();
    	}
    	else{
    		throw new UserNotFoundException("User Not found.");
    	}
		
	}



	@Override
	public boolean changePassword(String email,String oldPassword ,String newPassword)throws UserNotFoundException ,PasswordDoesNotMatchException{
		Optional<User> isUserExists = userAuthRepo.findById(email);
		
		if(!isUserExists.isEmpty()) {
			if(!isUserExists.get().getPassword().equals(oldPassword)) {
				throw new PasswordDoesNotMatchException();
			}
    		userAuthRepo.changePassword(newPassword, email);
    		return true;
    	}
    	else{
    		throw new UserNotFoundException("User Not found.");
    	}
	}
}
