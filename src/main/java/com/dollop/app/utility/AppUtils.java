package com.dollop.app.utility;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dollop.app.bean.UserDetails;
import com.dollop.app.handler.ResourceNotFound;
import com.dollop.app.respository.OtpRepository;
import com.dollop.app.respository.UserDetailsRepository;
@Component
public class AppUtils {
	
	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	

	public String generateOtp() {
		Random random=new Random();
		Integer otp = random.nextInt(1000,9999);
		
		return String.valueOf(otp);
	}
	
	public UserDetails checkValidEmailorNot(String email)
	{
		return  userDetailsRepository.findById(email).orElseThrow(()->new  ResourceNotFound(Constants.UNREGISTERED_EMAIL));
		
	}
	
	
	
	
	
	
	
	

}
