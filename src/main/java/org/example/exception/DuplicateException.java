package org.example.exception;

public class DuplicateException extends Exception {

	public DuplicateException() {
    }

    public DuplicateException(String msg) {
        super(msg);
    }

    public DuplicateException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
