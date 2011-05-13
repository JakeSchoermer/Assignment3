package au.edu.uq.itee.csse2002.sem12011;

/**
 * This exception is thrown when an illegal operation is performed
 * on a deck of cards.
 */
public class BadDeckOperationException extends StudentsLifeException {

	public BadDeckOperationException(String message) {
		super(message);
	}

	public BadDeckOperationException(String message, Throwable ex) {
		super(message, ex);
	}
}
