package au.edu.uq.itee.csse2002.sem12011;

/** This exception is thrown when the user enters invalid input.
 */
public class UserException extends StudentsLifeException {

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable ex) {
		super(message, ex);
	}
}
