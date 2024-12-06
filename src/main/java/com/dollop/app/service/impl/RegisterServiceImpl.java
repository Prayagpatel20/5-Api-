package com.dollop.app.service.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dollop.app.bean.ForgotPassword;
import com.dollop.app.bean.Login;
import com.dollop.app.bean.Otp;
import com.dollop.app.bean.Register;
import com.dollop.app.bean.ResetPassword;
import com.dollop.app.bean.UserDetails;
import com.dollop.app.dto.ForgotPasswordDto;
import com.dollop.app.dto.LoginDto;
import com.dollop.app.dto.OtpDto;
import com.dollop.app.dto.RegisterDto;
import com.dollop.app.dto.ResetPasswordDto;
import com.dollop.app.handler.ResourceNotFound;
import com.dollop.app.response.ApiResponse;
import com.dollop.app.respository.OtpRepository;
import com.dollop.app.respository.RegisterRespository;
import com.dollop.app.respository.UserDetailsRepository;
import com.dollop.app.service.RegisterService;
import com.dollop.app.service.Validation;
import com.dollop.app.utility.AppUtils;
import com.dollop.app.utility.Constants;
import com.dollop.app.utility.EmailSender;
import com.dollop.app.utility.SecurityUtil;

import io.netty.util.Constant;

@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	private RegisterRespository registerRespository;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private Validation validation;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AppUtils appUtils;
	
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private SecurityUtil securityUtils;
	
	/*
	 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 */
	

	@Override
	public ApiResponse register(RegisterDto registerDto) {
		 validation.isEmailAlreadyExist(registerDto.getEmail());
		 System.err.println("not exist emial");
		 
		 Register register = mapper.map(registerDto,Register.class);
		 
		 
		 //generate otp
		 String generateOtp = appUtils.generateOtp();
		 
		 //send generated otp to email with NO type match
		 emailSender.sendOtp(register.getEmail(), generateOtp);
		 //otp successtully send to registererd email address then we save otp in out register object
		 Otp otp=Otp.builder().email(register.getEmail()).otp(generateOtp)
				 .attempt(Constants.OTP_ATTEMPT)
				 
				 .time(LocalDateTime.now().plusMinutes(2))
				 .type(Constants.OTP_TYPE_REGISTER).build();
		 otpRepository.save(otp);
		 System.out.println(LocalDateTime.now().plusMinutes(2));
		 System.err.println(LocalDateTime.now());

		 registerRespository.save(register);
		
		return new ApiResponse(Constants.OTP_SEND_REGISTRATION + otp.getOtp());
	}




	@Override
	public ApiResponse validateOtp(OtpDto otpDto) {
		ApiResponse apiResponse = new ApiResponse("No data Updated");
		Otp otp = mapper.map(otpDto, Otp.class);
		Otp newOtp = otpRepository.findById(otp.getEmail())
				.orElseThrow(() -> new ResourceNotFound(Constants.UNREGISTERED_EMAIL));
		// check type of user
		if (newOtp.getType().equals(otp.getType())) {// check time
			if (newOtp.getTime().isAfter(LocalDateTime.now())) 
			{
				// chekc attempt
				if (newOtp.getAttempt() > 0) 
				{

					// check otp
					if (newOtp.getOtp().equals(otp.getOtp())) 
					{


						// for registration
						if (newOtp.getType().equals(otp.getType()) && otp.getType().equals("register")) {

							// for registration
							Register register = registerRespository.findById(newOtp.getEmail())
									.orElseThrow(() -> new ResourceNotFound(Constants.UNREGISTERED_EMAIL));

							if (userDetailsRepository.existsById(register.getEmail()))
								throw new ResourceNotFound(Constants.EMAIL_ALREADY_EXIST);

							UserDetails userDetail = UserDetails.builder().email(register.getEmail())
									.password(register.getPassword()).address(register.getAddress()).isActive(true)
									.time(LocalDateTime.now()).phone(register.getPhone()).build();
							userDetailsRepository.save(userDetail);

							otpRepository.save(newOtp);
							registerRespository.delete(register);
							apiResponse.setMessage(Constants.REGISTERED_SUCCESSFULLY);


						} else if (newOtp.getType().equals(otp.getType()) && otp.getType().equals("login")) {
							// generate token
							String generateToken = securityUtils.generateToken(otp.getEmail(), 98855585,
									"access_token");
							System.err.println("token for login" + generateToken);

							apiResponse.setMessage(generateToken);
						} else {
							// forgot password

//					 apiResponse.setMessage("Write code for generate token for 2 min ");
							String generateToken = securityUtils.generateToken(newOtp.getEmail(), 555585585, "auth_token");
							System.err.println(generateToken + " generat for djfla;jfladjf");
							apiResponse.setMessage(generateToken);
						}

					} // check otp
					else {
						int attempt = newOtp.getAttempt();
						newOtp.setAttempt(--attempt);
						otpRepository.save(newOtp);

						if (attempt == 2)
							throw new ResourceNotFound(Constants.OTP_ATTEMPT_LEFT_2);
						if (attempt == 1)
							throw new ResourceNotFound(Constants.OTP_ATTEMPT_LEFT_1);
						throw new ResourceNotFound(Constants.USER_LOCKED +" this is our attempt last block");

					}

				} // chekc attempt
				else {
					if (!newOtp.getType().equals(Constants.OTP_TYPE_REGISTER)) {
						UserDetails userDetailss = userDetailsRepository.findById(newOtp.getEmail())
								.orElseThrow(() -> new ResourceNotFound(Constants.UNREGISTERED_EMAIL));

						userDetailss.setIsActive(false);
						userDetailss.setTime(LocalDateTime.now().plusMinutes(10));
						//userDetailss
						userDetailsRepository.save(userDetailss);
						throw new ResourceNotFound(Constants.USER_LOCKED +" user is locked after lock");
					}

					else {
						otpRepository.deleteById(newOtp.getEmail());
						throw new ResourceNotFound(Constants.RE_REGSISTER_USER);
					}

				}

			} // check time
			else
				throw new ResourceNotFound(Constants.OTP_EXPIRE);
		} else// check type of user
			throw new ResourceNotFound(Constants.INVALID_OTP_TYPE);

		newOtp.setAttempt(Constants.OTP_ATTEMPT);
		newOtp.setTime(LocalDateTime.now());
		otpRepository.save(newOtp);

		return apiResponse;

	}




	@Override
	public ApiResponse login(LoginDto loginDto) {
		ApiResponse apiResponse=new ApiResponse();
		Login login = mapper.map(loginDto, Login.class);
		UserDetails userDetails = appUtils.checkValidEmailorNot(login.getEmail());
		if(userDetails.getTime().isBefore(LocalDateTime.now())) {
			if(!userDetails.getIsActive()) {
				userDetails.setIsActive(true);
				userDetailsRepository.save(userDetails);
			}
		}else 
				throw new ResourceNotFound(Constants.USER_LOCKED);
				
		if(login.getPassword().equals(userDetails.getPassword())) {
			//generate otp 
			String generateOtp = appUtils.generateOtp();
			//send to email address
			emailSender.sendOtp(userDetails.getEmail(), generateOtp);
			
			
			//update otp and otp type
			Otp otp = otpRepository.findById(userDetails.getEmail()).orElseThrow(()-> new ResourceNotFound(Constants.UNREGISTERED_EMAIL));
			otp.setOtp(generateOtp);
			otp.setAttempt(Constants.OTP_ATTEMPT);
			otp.setTime(LocalDateTime.now().plusMinutes(2));
			otp.setType(Constants.OTP_TYPE_Login);
			
			//save otp
			otpRepository.save(otp);
			apiResponse.setMessage(Constants.OTP_SEND_LOGIN + " otp is "+ generateOtp);
			
			
		}
		else
			throw new ResourceNotFound(Constants.INVALID_CERDENTAILS);
		
		
		
		
		return  apiResponse;
	}




	@Override
	public ApiResponse forgotPassword(ForgotPasswordDto forgotPasswordDto) {
		ForgotPassword forgotPassword = mapper.map(forgotPasswordDto, ForgotPassword.class);
		Otp otp = otpRepository.findById(forgotPassword.getEmail()).orElseThrow(()-> new ResourceNotFound(Constants.UNREGISTERED_EMAIL));
		String generateOtp = appUtils.generateOtp();
		UserDetails userDetails = userDetailsRepository.findById(forgotPassword.getEmail()).orElseThrow(()-> 
		new ResourceNotFound(Constants.UNREGISTERED_EMAIL));
		
		if(userDetails.getTime().isBefore(LocalDateTime.now())) {
			if(!userDetails.getIsActive()) {
				userDetails.setIsActive(true);
				userDetailsRepository.save(userDetails);
			}}
			else
				throw new ResourceNotFound(Constants.USER_LOCKED);
		
		
		emailSender.sendOtp( forgotPassword.getEmail(),generateOtp);
		otp.setOtp(generateOtp);
		otp.setType(Constants.OTP_TYPE_FORGOT_PASSWORD);
		otp.setAttempt(Constants.OTP_ATTEMPT);
		otp.setTime(LocalDateTime.now().plusMinutes(2));
		otpRepository.save(otp);
		
		return new ApiResponse(Constants.OTP_SEND_FORGOT_PASSWORD +" otp is "+ generateOtp);
	}




	@Override
	public ApiResponse resetPassword(ResetPasswordDto resetPasswordDto) {
		
		String token = securityUtils.extractToken();
		String tokenType = securityUtils.getTokenType();
		String email = securityUtils.getemail();
		System.out.println("token is "+ token);
		if(!tokenType.equals("auth_token"))
			throw new ResourceNotFound("INvalid token");
		System.out.println("token type is " +tokenType+"..................................");
		System.err.println(email);
		UserDetails userDetails = userDetailsRepository.findById(email).orElseThrow(()-> 
		new ResourceNotFound(Constants.UNREGISTERED_EMAIL));
		
		if(userDetails.getTime().isBefore(LocalDateTime.now())) {
			if(!userDetails.getIsActive()) {
				userDetails.setIsActive(true);
			}
				ResetPassword resetPassword = mapper.map(resetPasswordDto,ResetPassword.class);
				userDetails.setPassword(resetPassword.getPassword());
				userDetailsRepository.save(userDetails);
			}
			else
				throw new ResourceNotFound(Constants.USER_LOCKED);
		
	
		
		
		
		
		// chek token and save password
		
		//save password
		return new ApiResponse(Constants.PASSWORD_CHANGE_SUCCESSFULLY);
	}
	
	
	
	
	

}
