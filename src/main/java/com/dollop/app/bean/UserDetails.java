package com.dollop.app.bean;

import java.time.LocalDateTime;

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
public class UserDetails {
	
	@Id
	   private String email;
		
		private String password;
		
		private String address;
		
		private String phone;
		
		private Boolean isActive;
		private LocalDateTime time;
		
		

}
