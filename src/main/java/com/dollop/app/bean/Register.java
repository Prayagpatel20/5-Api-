package com.dollop.app.bean;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Register {
	
	@Id
	   private String email;
		
		private String password;
		
		private String address;
		
		private String phone;
		
	
		
		
		

}
