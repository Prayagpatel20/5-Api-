package com.dollop.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dollop.app.bean.Register;

@Repository
public interface RegisterRespository extends JpaRepository<Register, String>{

}
