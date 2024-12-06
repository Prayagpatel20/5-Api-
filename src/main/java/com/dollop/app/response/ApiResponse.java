package com.dollop.app.response;


import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ApiResponse {
	
	private String message;
	private HttpStatus status;
	private Object object;
	public ApiResponse(String message) {
		super();
		this.message = message;
	}

}
