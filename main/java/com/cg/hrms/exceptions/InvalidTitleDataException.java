package com.cg.hrms.exceptions;

public class InvalidTitleDataException extends RuntimeException {

	public InvalidTitleDataException() {
		super();
	}

	public InvalidTitleDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidTitleDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTitleDataException(String message) {
		super(message);
	}

	public InvalidTitleDataException(Throwable cause) {
		super(cause);
	}

}
