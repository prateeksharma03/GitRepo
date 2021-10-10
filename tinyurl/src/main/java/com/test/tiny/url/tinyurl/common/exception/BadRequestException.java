package com.test.tiny.url.tinyurl.common.exception;


/**
 * @author prateek
 * 
 */
public class BadRequestException extends BaseException {
     private static final long serialVersionUID = 1L;
     
     private int errorCode = 400;
     public BadRequestException(String msg) {
          super(msg);
     }
     
     public int getErrorCode() {
 		return errorCode;
 	 }
     
}
