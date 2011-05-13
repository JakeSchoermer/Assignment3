package au.edu.uq.itee.csse2002.sem12011;

/**
 * An invalid game action has been requested.
 */
public class InvalidActionException extends RuntimeException {

	public InvalidActionException(String message) {
		super(message);
	}

	public InvalidActionException(String message, Throwable cause) {
		super(message, cause);
	}
}
