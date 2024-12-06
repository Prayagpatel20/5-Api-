package com.dollop.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dollop.app.bean.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

}
