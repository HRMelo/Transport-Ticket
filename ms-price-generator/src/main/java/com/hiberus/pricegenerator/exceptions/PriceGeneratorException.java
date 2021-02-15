package com.hiberus.pricegenerator.exceptions;

public class PriceGeneratorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -160154409972755618L;
	
	public PriceGeneratorException() {
		super();
	}
	
	public PriceGeneratorException(String msg) {
		super(msg);
	}
	
	public PriceGeneratorException(String msg, Throwable thowable) {
		super(msg, thowable);
	}

}
