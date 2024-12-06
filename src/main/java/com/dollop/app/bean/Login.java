package com.dollop.app.bean;

import com.dollop.app.dto.LoginDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Login {
	@NotBlank(message = "User Password not be null")
	private String email;
	@NotBlank(message = "User Password not be null")
	private String password;

}
