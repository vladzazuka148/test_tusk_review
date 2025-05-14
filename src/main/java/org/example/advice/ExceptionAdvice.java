package org.example.advice;

import org.example.exception.DuplicateException;
import org.example.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {
	private static Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody String systemError(Exception ex) {
		log.error("System error cause {}, message {}", ex.getCause(), ex.getMessage());
		return "System error:" + ex.getMessage();
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody String handleNotFoundException(NotFoundException ex) {
		String err = ex.getMessage() == null ? "Object not found" : ex.getMessage();
		log.error("NotFoundException cause {}, message {}", ex.getCause(), ex.getMessage());
		return err;
	}

	@ExceptionHandler(DuplicateException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody String handleDuplicateException(DuplicateException ex) {
		String err = ex.getMessage() == null ? "Object already exists" : ex.getMessage();
		log.error("DuplicateException cause {}, message {}", ex.getCause(), ex.getMessage());
		return err;
	}

}
