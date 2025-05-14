package org.example.exception;

public class NotFoundException extends Exception {

	public NotFoundException() {
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
