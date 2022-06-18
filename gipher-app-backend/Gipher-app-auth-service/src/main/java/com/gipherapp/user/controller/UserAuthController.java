package com.gipherapp.user.controller;

import com.gipherapp.user.service.UserAuthService;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gipherapp.user.model.User;
import com.gipherapp.user.util.exception.PasswordDoesNotMatchException;
import com.gipherapp.user.util.exception.UserAlreadyExistsException;
import com.gipherapp.user.util.exception.UserNotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {


	static final long EXPIRATIONTIME = 300000;
	private Map<String, String> map = new HashMap<>();
	
	
	@Autowired
	private UserAuthService userAuthService;
	
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws UserNotFoundException{
    	try {
    		User userById = userAuthService.findByEmailAndPassword(user.getEmail(), user.getPassword());
    		if(userById == null) {
    			userAuthService.saveUser(user);
    			logger.info("In controller - {}", "User is registered successfully.");
    			return new ResponseEntity<User>(user, HttpStatus.CREATED);
    		}
    	}catch(UserAlreadyExistsException e) {
    		map.clear();
    		map.put("message","User already exists." );
    		logger.info("In controller - {}", "User already exists.");
    		return new ResponseEntity<>(map,HttpStatus.CONFLICT);
    	} 
    	logger.info("In controller - {}", "User already exists.");
    	map.clear();
		map.put("message","User already exists." );
    	return new ResponseEntity<>(map,HttpStatus.CONFLICT);
	}

    @PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException{
    	String jwtToken = "";
    	try {
    		jwtToken = getToken(user.getEmail(), user.getPassword());
    		map.clear();
    		map.put("message", "user successfully logged in");
    		map.put("token", jwtToken);
    	}
    	catch(Exception e) {
    		String exceptionMsg = e.getMessage();
    		map.clear();
    		map.put("token", null);
    		map.put("message", exceptionMsg);
    		logger.info("In controller - {}", "Unauthorized User.");
    		return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    	}
    	logger.info("In controller - {}", "Authorized User.");
    	return new ResponseEntity<>(map, HttpStatus.OK);
	}
    
    @PutMapping("/updateprofile")
    public ResponseEntity<?> updateUserProfile(@RequestBody User user) throws UserNotFoundException{
    	
    	try {
    		boolean userUpdated =  userAuthService.updateUserProfile(user);
    		if(userUpdated) {
    			map.clear();
        		map.put("message", "user profile has been succesfully updated");
        		
    		}
    		
    	}catch(UserNotFoundException e) {
    		return new ResponseEntity<>("User Not found", HttpStatus.NOT_FOUND);
    		
    	}
    	return new ResponseEntity<>(map, HttpStatus.OK);
    	
    }
    
    @PutMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> data) throws UserNotFoundException, PasswordDoesNotMatchException{
    	
    	try {
    		boolean passwordChanged =  userAuthService.changePassword( data.get("email"),data.get("password"),data.get("newPassword"));
    		if(passwordChanged) {
    			map.clear();
        		map.put("message", "password has been succesfully changed");
        		
    		}
    		
    	}catch(UserNotFoundException e) {
    		return new ResponseEntity<>("User Not found", HttpStatus.NOT_FOUND);
    		
    	}catch(PasswordDoesNotMatchException e) {
    		return new ResponseEntity<>("Password Does not match"+e.getMessage(), HttpStatus.UNAUTHORIZED);
    	}
    	return new ResponseEntity<>(map, HttpStatus.OK);
    	
    }
    
    @GetMapping("/userprofile/{email}")
    public ResponseEntity<?> getUserProfile(@PathVariable String email){
    	try {
    		User user =userAuthService.getUserDetail(email);
    		return new ResponseEntity<>(user, HttpStatus.OK);
    		
    	}catch(UserNotFoundException e) {
    		return new ResponseEntity<>("User Not found", HttpStatus.NOT_FOUND);
    		
    	}
    	
    }
    
    public String getToken(String userName, String password) throws Exception{
    	if(userName == null || password == null) {
    		throw new ServletException("Please fill in username and password.");
    	}
    	
    	User isUserExists = userAuthService.findByEmailAndPassword(userName, password);
    	
    	if(isUserExists == null ) {
    		throw new ServletException("Invalid Credentials.");
    	}
    	
    	Claims claims = Jwts.claims().setSubject(userName);
    	
    	String jwtToken = Jwts.builder().setClaims(claims)
    			.setIssuedAt(new Date())
    			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME + 8*24*60*60*1000))
    			.signWith(SignatureAlgorithm.HS256, "secretkey")
    			.compact();
    	
    	logger.info("In controller - {}", "JWT Token created Successfully.");
    	return jwtToken;
    }
}
