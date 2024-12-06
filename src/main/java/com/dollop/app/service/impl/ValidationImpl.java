package com.dollop.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.bean.Otp;
import com.dollop.app.bean.Register;
import com.dollop.app.handler.EmailAlreadyExist;
import com.dollop.app.handler.ResourceNotFound;
import com.dollop.app.respository.OtpRepository;
import com.dollop.app.respository.RegisterRespository;
import com.dollop.app.respository.UserDetailsRepository;
import com.dollop.app.service.Validation;
import com.dollop.app.utility.Constants;

@Service
public class ValidationImpl implements Validation{
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private RegisterRespository registerRespository;
	
	@Autowired
	private OtpRepository otpRepository;
	
	 

	@Override
	public Boolean isEmailAlreadyExist(String email) {
		boolean existsById = userDetailsRepository.existsById(email);
		if(existsById)
			throw new EmailAlreadyExist(Constants.EMAIL_ALREADY_EXIST);
		return false;	
	}
	
	



	@Override
	public Boolean validateOtp(String email,String otp, String type) {
		 return true;
	}
	
	
	

}
