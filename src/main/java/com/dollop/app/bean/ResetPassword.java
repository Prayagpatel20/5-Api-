package com.dollop.app.bean;

import com.dollop.app.dto.ResetPasswordDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPassword {
	private String password;

}
