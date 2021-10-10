package com.test.tiny.url.tinyurl.common.exception;



/**
 * @author prateek
 *
 */
public class BaseException extends RuntimeException {
     private static final long  serialVersionUID = 1L;

     private int errorCode;
     
     public BaseException(int errorCode) {
         this.errorCode = errorCode;
    }

     public BaseException(String msg) {
          super(msg);
     }

    
}
