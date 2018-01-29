package stargate.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NoBalanceException extends RuntimeException {
	//private static final long serialVersionUID = 405802649322364762L;

	public NoBalanceException(String message) {
		super(message);
	}

	public NoBalanceException(String message, Throwable cause) {
		super(message, cause);
	}
}