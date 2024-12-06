package com.dollop.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.bean.Otp;

public interface OtpRepository  extends JpaRepository<Otp, String> {

}
