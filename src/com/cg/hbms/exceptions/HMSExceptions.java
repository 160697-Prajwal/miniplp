package com.cg.hbms.exceptions;

public class HMSExceptions extends Exception {

	private static final long serialVersionUID = 1L;
	String message;

	public HMSExceptions(String message) {
		super();
		this.message = message;
	}

	public HMSExceptions() {
	}

}
