package au.edu.uq.itee.csse2002.sem12011;

/**
 * A Deck object denotes an deck of playing cards in the Student's Life game.
 * It supports operations needed during game play; e.g. shuffling the Cards, 
 * getting the next Card from the deck and returning a Card to the deck.  
 * <p>
 * A Deck is created with an initial set of cards, and must never contain more 
 * than that number of cards.  A Card must never appear more than once in the 
 * same Deck.
 */
public interface Deck extends Iterable<Card> {
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle();
	
	/**
	 * Gets the card off the top of the deck.
	 * 
	 * @return a card from the top of the deck, or null if the deck is empty
	 */
	public Card pickUp();
	
	/**
	 * Puts a card back on the bottom of the deck.
	 * 
	 * @param card	the card to be returned to the deck
	 * @throws BadDeckOperationException this should be thrown if you
	 *     attempt to putBack a card into a full deck, or if
	 *     you attempt to putBack a null card, or a card that is 
	 *     already in the deck.
	 */
	public void putBack(Card card) throws BadDeckOperationException;
	
	/**
	 * Gets the full deck size.
	 * 
	 * @return the number of cards in the initial deck
	 */
	public int fullSize();
	
	/**
	 * Gets the current size of the deck.
	 * 
	 * @return the number of cards currently in the deck
	 */
	public int size();
	
	/**
	 * Returns a comma separated list of IDs for each card in the deck from 
	 * the top down.
	 * <p>
	 * E.g for the following deck:
	 * <pre>
	 * 		10
	 * 		13
	 * 		4
	 * 		1
	 * </pre>
	 * The expected string returned would be: "10,13,4,1"
	 * 
	 */
	public String toString();
	
}
