package com.test.tiny.url.tinyurl.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.tiny.url.tinyurl.common.exception.BadRequestException;
import com.test.tiny.url.tinyurl.common.exception.BaseException;
import com.test.tiny.url.tinyurl.common.exception.ForbiddenException;
import com.test.tiny.url.tinyurl.common.exception.InternalServerErrorException;
import com.test.tiny.url.tinyurl.common.exception.MethodNotAllowedException;
import com.test.tiny.url.tinyurl.common.exception.PreconditionFailedException;
import com.test.tiny.url.tinyurl.vo.ResponseWrapper;

/**
 * @author Prateek
 * 
 * This class is a single point to manage exceptions of entire application
 *
 */
@ControllerAdvice(basePackages = "com.test.tiny.url.tinyurl")
public class GlobalExceptionHandler {
	//TODO need to add logger config properly
	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseWrapper> handleException(HttpServletRequest request, Exception ex) {
		logger.error("Error -handleException " + ex.getMessage(), ex);
		return getResponse(new InternalServerErrorException(ex.getMessage()), 500);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ResponseWrapper> handleBadRequestException(HttpServletRequest request,
			BadRequestException ex) {
		logger.debug("Error -handleBadRequestException " + ex.getMessage());
		return getResponse(ex, ex.getErrorCode());
	}

	@ExceptionHandler(PreconditionFailedException.class)
	public ResponseEntity<ResponseWrapper> handlePreconditionFailedException(HttpServletRequest request,
			PreconditionFailedException ex) {
		logger.debug("Error -handlePreconditionFailedException " + ex.getMessage());
		return getResponse(ex, ex.getErrorCode());
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ResponseWrapper> handleForbiddenException(HttpServletRequest request, ForbiddenException ex) {
		logger.debug("Error -handleForbiddenException " + ex.getMessage());
		return getResponse(ex, ex.getErrorCode());
	}

	@ExceptionHandler(MethodNotAllowedException.class)
	public ResponseEntity<ResponseWrapper> handleMethodNotAllowedException(HttpServletRequest request,
			MethodNotAllowedException ex) {
		logger.debug("Error -handleMethodNotAllowedException " + ex.getMessage());
		return getResponse(ex, ex.getErrorCode());
	}

	/**
	 * Method to prepare response in case of exceptions
	 * @param ex
	 * @param errorCode
	 * @return
	 */
	private ResponseEntity<ResponseWrapper> getResponse(BaseException ex, int errorCode) {
		ResponseWrapper wrapper = new ResponseWrapper();
		wrapper.setMessage(ex.getMessage());
		wrapper.setStatus("Failed");
		HttpStatus httpSt;
		switch (errorCode) {
			case 400:
				httpSt = HttpStatus.BAD_REQUEST;
				break;
			case 500:
				httpSt = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			default:
				httpSt = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(wrapper, httpSt);
	}

}
