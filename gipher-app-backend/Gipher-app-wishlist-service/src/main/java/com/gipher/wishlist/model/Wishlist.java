package com.gipher.wishlist.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document
public class Wishlist {
	
	 	@Id
	    private String id;
	 	private String userId;
	 	private String gifId;
	 	private String title;
	 	private String name;
	 	private String imgUrl;
	 	private LocalDate createdDate;
	 	
	 	

}
