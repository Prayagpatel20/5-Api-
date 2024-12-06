package com.dollop.app.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dollop.app.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFound.class )
	public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFound found) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(found.getMessage()),HttpStatus.BAD_REQUEST);	
		}
	
	@ExceptionHandler(EmailAlreadyExist.class )
	public ResponseEntity<ApiResponse> emailAlreadyExist(EmailAlreadyExist found) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(found.getMessage()),HttpStatus.EXPECTATION_FAILED);	
		}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> invalidValidation(MethodArgumentNotValidException invalidValidation) {

		Map<String, String> map = new HashMap<>();

		List<FieldError> fieldErrors = invalidValidation.getBindingResult().getFieldErrors();
		fieldErrors.forEach(er -> {
			map.put(er.getField(), er.getDefaultMessage());
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.builder() 	.object(map).build());
	}

}
