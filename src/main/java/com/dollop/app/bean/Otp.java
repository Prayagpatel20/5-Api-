package com.dollop.app.bean;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Otp {
	@Id
	private String email;
	private String otp;
	private String type;
	
    private  int attempt;
	
	private LocalDateTime time;

}
