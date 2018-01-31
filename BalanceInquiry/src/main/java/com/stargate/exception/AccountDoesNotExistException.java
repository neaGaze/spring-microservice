package com.stargate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class AccountDoesNotExistException extends RuntimeException {

       
       private static final long serialVersionUID = 405802649322364762L;

       public AccountDoesNotExistException(String msg){
              super(msg);
       }
       public AccountDoesNotExistException(String msg, Throwable cause) {
              super(msg, cause);
       }
}
