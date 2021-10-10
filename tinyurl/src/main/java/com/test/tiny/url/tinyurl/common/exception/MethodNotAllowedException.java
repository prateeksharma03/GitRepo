package com.test.tiny.url.tinyurl.common.exception;

/**
 * @author prateek
 * 
 */

public class MethodNotAllowedException extends BaseException {
     /**
     * 
     */
     private static final long serialVersionUID = 1L;

     private int errorCode = 405;
     
     public MethodNotAllowedException(String msg) {
          super(msg);
     }
     
     public int getErrorCode() {
  		return errorCode;
  	 }

}
