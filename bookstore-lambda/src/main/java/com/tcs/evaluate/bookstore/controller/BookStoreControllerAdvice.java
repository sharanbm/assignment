package com.tcs.evaluate.bookstore.controller;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tcs.evaluate.bookstore.model.BookConflictException;
import com.tcs.evaluate.bookstore.model.BookNotFoundException;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error")
@ResponseBody
public class BookStoreControllerAdvice {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(BookNotFoundException.class)
	public VndErrors bookNotFoundException(BookNotFoundException e) {
		return this.error(e, "Something is not right with your request, Please refer to the message below");
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public VndErrors constraintViolationException(ConstraintViolationException e) {
		return this.error(e, "Something is not right with your request, Please refer to the message below");
	}

	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(BookConflictException.class)
	public VndErrors bookConflictException(BookConflictException e) {
		return this.error(e, "Something is not right with your request, Please refer to the message below");
	}

	private <E extends Exception> VndErrors error(E e, String logref) {
		String msg = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
		return new VndErrors(logref, msg);
	}

}