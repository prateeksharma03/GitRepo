package com.test.tiny.url.tinyurl.common.exception;


/**
 * @author Prateek
 * 
 */
public class InternalServerErrorException extends BaseException {
     /**
     * 
     */
     private static final long serialVersionUID = 1L;

     private int errorCode = 500;
     public InternalServerErrorException(String msg) {
          super(msg);
          
     }
	 public int getErrorCode() {
		return errorCode;
	 }
     
}
