package com.cg.hrms.exceptions;

public class TitleNotFoundException extends RuntimeException {

	public TitleNotFoundException() {
		super();
	}

	public TitleNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TitleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TitleNotFoundException(String message) {
		super(message);
	}

	public TitleNotFoundException(Throwable cause) {
		super(cause);
	}

}
