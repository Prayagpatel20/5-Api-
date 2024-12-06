package com.dollop.app.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
	

	@NotBlank(message = "User id not be null")
	@NotNull(message = "user id must contain email")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Email must be  well formatted")
   private String email;
	
	@NotBlank(message = "Password not be null")
	@Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
	,message = "Password contain one digit, one lowercase character,one uppercase character"
			+ ",one special character,At least 6 characters ")
	private String password;
	
	@NotBlank(message = "NOt be null address")
	private String address;
	
	@NotBlank(message = "Phone number not be null")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Phone number must be 10 digit")
	private String phone;
	
	

}
