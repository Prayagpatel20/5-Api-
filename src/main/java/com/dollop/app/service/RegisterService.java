package com.dollop.app.service;

import com.dollop.app.dto.ForgotPasswordDto;
import com.dollop.app.dto.LoginDto;
import com.dollop.app.dto.OtpDto;
import com.dollop.app.dto.RegisterDto;
import com.dollop.app.dto.ResetPasswordDto;
import com.dollop.app.response.ApiResponse;

public interface RegisterService {
	
	public ApiResponse register(RegisterDto registerDto);
	
	public ApiResponse validateOtp(OtpDto otp);
	
	public ApiResponse login(LoginDto loginDto);
	
	public ApiResponse forgotPassword(ForgotPasswordDto forgotPasswordDto);
	
	public ApiResponse resetPassword(ResetPasswordDto resetPasswordDto);
	

}
