package com.expirian.exceptions;

public class InvalidExpressionException extends Exception {

	private static final long serialVersionUID = 7160971699956069351L;

	public InvalidExpressionException() {
	}
	
	public InvalidExpressionException(String message){
		super(message);
	}
	
	public InvalidExpressionException(Throwable cause){
		super(cause);
	}
	
	public InvalidExpressionException(String message, Throwable cause){
		super(message,cause);
	}

}
