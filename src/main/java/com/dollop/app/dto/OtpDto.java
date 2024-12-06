package com.dollop.app.dto;

import java.time.LocalDateTime;

import com.dollop.app.bean.Otp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpDto {
	@NotBlank(message = "User id not be null")
	@NotNull(message = "user id must contain email")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Email must be  well formatted")
	private String email;
	@Pattern(regexp = "^\\d{4}$" ,message = "Otp must 4 digit only")
	private String otp;
	@Pattern(regexp = "login|register|forgotPassword",
			message = "type must be login or register or forgotPassword")
	private String type;
	private  int attempt;
	
	private LocalDateTime time;
	
	


}
