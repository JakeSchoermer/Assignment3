package au.edu.uq.itee.csse2002.sem12011;

/**
 * This exception is thrown when a problem occurs while assembling
 * a Deck of cards.
 */
public class BadDeckException extends StudentsLifeException {

	public BadDeckException(String message) {
		super(message);
	}

	public BadDeckException(String message, Throwable ex) {
		super(message, ex);
	}
}
