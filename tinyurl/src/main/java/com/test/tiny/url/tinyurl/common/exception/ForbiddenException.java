package com.test.tiny.url.tinyurl.common.exception;


/**
 * @author prateek
 * 
 */
public class ForbiddenException extends BaseException {

	private static final long serialVersionUID = 1L;

	private int errorCode = 403;
     public ForbiddenException(String msg) {
          super(msg);
     }
     
     public int getErrorCode() {
  		return errorCode;
  	 }
}