package com.gipherapp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.gipherapp.user.model.User;


/*
* This class is implementing the JpaRepository interface for Note.
* Annotate this class with @Repository annotation
*/

@Repository
public interface UserAuthRepository extends JpaRepository<User,String>{

	public User findByEmailAndPassword(String email, String password);
	
	@Transactional
	@Modifying
	@Query("update User u set u.password = ?1 where u.email = ?2")
	int changePassword(String password,String email);
	
	


}
