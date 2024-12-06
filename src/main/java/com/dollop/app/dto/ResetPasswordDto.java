package com.dollop.app.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordDto {
	@NotBlank(message = "Password not be null")
	@Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
	,message = "Password contain one digit, one lowercase character,one uppercase character"
			+ ",one special character,At least 6 characters ")
	@NotBlank(message = "Invalid Password ! Please check it")
	private String password;

}
