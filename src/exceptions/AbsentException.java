package exceptions;

public class AbsentException extends Exception {

	public AbsentException() {
	}

	public AbsentException(String message) {
		super(message);
	}

	public AbsentException(Throwable cause) {
		super(cause);
	}

	public AbsentException(String message, Throwable cause) {
		super(message, cause);
	}
}
