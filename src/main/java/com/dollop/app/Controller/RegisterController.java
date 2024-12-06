package com.dollop.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.bean.Otp;
import com.dollop.app.bean.Register;
import com.dollop.app.dto.ForgotPasswordDto;
import com.dollop.app.dto.LoginDto;
import com.dollop.app.dto.OtpDto;
import com.dollop.app.dto.RegisterDto;
import com.dollop.app.dto.ResetPasswordDto;
import com.dollop.app.response.ApiResponse;
import com.dollop.app.service.RegisterService;
import com.dollop.app.service.impl.RegisterServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/api")
public class RegisterController {
	@Autowired
	private RegisterService registerService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterDto registerDto){
	
		return new ResponseEntity<ApiResponse>(registerService.register(registerDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/checkOtp")
	public ResponseEntity<ApiResponse> validateOtp(@Valid @RequestBody OtpDto otp){
		return new ResponseEntity<ApiResponse>(registerService.validateOtp(otp),HttpStatus.CREATED);

	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDto loginDto){
		return new ResponseEntity<>(registerService.login(loginDto),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<ApiResponse> forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto){
		System.err.println("inside forgotpassword");
		return new ResponseEntity<ApiResponse>(registerService.forgotPassword(forgotPasswordDto),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto){
		return new ResponseEntity<ApiResponse>(registerService.resetPassword(resetPasswordDto),HttpStatus.OK);
	}
	


}
