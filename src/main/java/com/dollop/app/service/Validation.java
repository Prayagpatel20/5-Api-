package com.dollop.app.service;


public interface Validation {

	public Boolean isEmailAlreadyExist(String email);
	
	public Boolean validateOtp(String email,String otp,String type);
	

}
