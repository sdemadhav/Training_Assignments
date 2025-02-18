package com.example.empManageApp.Exceptions;

class InvalidChoiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidChoiceException() {
        super();
    }

    public InvalidChoiceException(String msg) {
        super(msg);
    }
}
