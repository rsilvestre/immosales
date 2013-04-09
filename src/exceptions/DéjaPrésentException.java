package exceptions;

public class DéjaPrésentException extends Exception {

	public DéjaPrésentException() {
	}

	public DéjaPrésentException(String message) {
		super(message);
	}

	public DéjaPrésentException(Throwable cause) {
		super(cause);
	}

	public DéjaPrésentException(String message, Throwable cause) {
		super(message, cause);
	}
}
