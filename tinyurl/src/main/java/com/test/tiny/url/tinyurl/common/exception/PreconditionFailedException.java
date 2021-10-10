package com.test.tiny.url.tinyurl.common.exception;


/**
 * @author prateek
 * 
 */
public class PreconditionFailedException extends BaseException {
     /**
     * 
     */
     private static final long serialVersionUID = 1L;

     private int errorCode = 413;
     public PreconditionFailedException(String msg) {
          super(msg);
     }
     
     public int getErrorCode() {
  		return errorCode;
  	 }

}
