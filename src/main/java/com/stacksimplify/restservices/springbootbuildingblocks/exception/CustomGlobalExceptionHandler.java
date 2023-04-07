package com.stacksimplify.restservices.springbootbuildingblocks.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
 CustomErrorDetails customErrorDeatils = new CustomErrorDetails(new Date() , "From CustomGlobalExceptionHandler " , ex.getMessage());
 
 return new ResponseEntity<>(customErrorDeatils ,HttpStatus.BAD_REQUEST );
		
	}
	
	@Override 
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		pageNotFoundLogger.warn(ex.getMessage());
		CustomErrorDetails customErrorDeatils = new CustomErrorDetails(new Date() , "From CustomGlobalExceptionHandler for MNS" , ex.getMessage());
		 
		 return new ResponseEntity<>(customErrorDeatils ,HttpStatus.BAD_REQUEST );
		
	}
	@ExceptionHandler(UserNameNotFoundException.class)
protected ResponseEntity<Object> handleUserNameNotFoundException(
			UserNameNotFoundException ex, WebRequest request) {
		
 CustomErrorDetails customErrorDeatils = new CustomErrorDetails(new Date()  ,ex.getMessage() , request.getDescription(false));
 
 return new ResponseEntity<>(customErrorDeatils ,HttpStatus.NOT_FOUND );
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> handleUserNotFoundException(
			UserNotFoundException ex, WebRequest request) {
			
	 CustomErrorDetails customErrorDeatils = new CustomErrorDetails(new Date()  ,ex.getMessage() , request.getDescription(false));
	 
	 return new ResponseEntity<>(customErrorDeatils ,HttpStatus.NOT_FOUND );
			
		}	
	

	@ExceptionHandler(ConstraintViolationException.class)
	
	protected ResponseEntity<Object> handleConstraintViolationException (
			ConstraintViolationException  ex, WebRequest request) {
			
	 CustomErrorDetails customErrorDeatils = new CustomErrorDetails(new Date()  ,ex.getMessage() , request.getDescription(false));
	 return new ResponseEntity<>(customErrorDeatils ,HttpStatus.BAD_REQUEST );
			
		}	
	
	
}
