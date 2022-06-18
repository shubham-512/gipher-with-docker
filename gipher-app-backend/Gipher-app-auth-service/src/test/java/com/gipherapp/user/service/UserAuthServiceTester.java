package com.gipherapp.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.gipherapp.user.model.User;
import com.gipherapp.user.repository.UserAuthRepository;
import com.gipherapp.user.util.exception.PasswordDoesNotMatchException;
import com.gipherapp.user.util.exception.UserAlreadyExistsException;
import com.gipherapp.user.util.exception.UserNotFoundException;


public class UserAuthServiceTester {
	
	 	@Mock
	    private UserAuthRepository userAuthRepository;

	    private User user1;
	    
	    @InjectMocks
	    private UserAuthServiceImpl userAuthServiceImpl;

	    Optional<User> optional;
	    
	    @BeforeEach
		public void setUp() {
			MockitoAnnotations.initMocks(this);
			user1 = new User("ritika@gmail.com", "ritika123", "Up", "7895674532","Ritika");
			optional = Optional.of(user1);
			
		}
	    
	    @Test
	    public void testSaveUserSuccess() throws UserAlreadyExistsException {

	        Mockito.when(userAuthRepository.save(user1)).thenReturn(user1);
	        boolean flag = userAuthServiceImpl.saveUser(user1);
	        assertEquals(true, flag);

	    }
	    
	    
	    @Test
	    public void testSaveUserFailure() {

	        Mockito.when(userAuthRepository.findById("ritika@gmail.com")).thenReturn(optional);
	        Mockito.when(userAuthRepository.save(user1)).thenReturn(user1);
	        assertThrows(
	        		UserAlreadyExistsException.class,
	                    () -> { userAuthServiceImpl.saveUser(user1); });

	    }
	    
	    @Test
	    public void testFindEmailAndPassword() throws UserNotFoundException {
	        Mockito.when(userAuthRepository.findByEmailAndPassword("ritika@gmail.com", "Ritika")).thenReturn(user1);
	        User fetchedUser = userAuthServiceImpl.findByEmailAndPassword("ritika@gmail.com", "Ritika");
	        assertEquals("ritika@gmail.com", fetchedUser.getEmail());
	    }
	    
	    @Test
	    public void testUpdateUserProfileSuccess() throws UserNotFoundException {
	    	
	    	Mockito.when(userAuthRepository.findById("ritika@gmail.com")).thenReturn(optional);
	        Mockito.when(userAuthRepository.save(user1)).thenReturn(user1);
	        boolean flag = userAuthServiceImpl.updateUserProfile(user1);
	        assertEquals(true, flag);
	        
	    }
	    
	    @Test
	    public void testUpdateUserProfileFailure()  {
	        Mockito.when(userAuthRepository.save(user1)).thenReturn(user1);
	        assertThrows(
	        		UserNotFoundException.class,
	                    () -> { userAuthServiceImpl.updateUserProfile(user1); });
	           
	    }
	    
	    @Test
	    public void testChangePasswordSuccess() throws UserNotFoundException ,PasswordDoesNotMatchException {
	    	Mockito.when(userAuthRepository.findById("ritika@gmail.com")).thenReturn(optional);
	    	Mockito.when(userAuthRepository.changePassword(user1.getPassword(), user1.getEmail())).thenReturn(1);
	    	boolean flag = userAuthServiceImpl.changePassword(user1.getEmail(), user1.getPassword(), "newPaasword");
	    	assertEquals(true, flag);
	    	
	    }
	    
	    @Test
	    public void testChangePasswordNotMatchFailure() throws UserNotFoundException {
	    	Mockito.when(userAuthRepository.findById("ritika@gmail.com")).thenReturn(optional);
	    	assertThrows(
	    			PasswordDoesNotMatchException.class,
	                    () -> { userAuthServiceImpl.changePassword(user1.getEmail(), "differentPassword", "newPaasword"); });
	    	
	    	
	    }
	    

	    

}
