package au.edu.uq.itee.csse2002.sem12011;

/**
 * A Card object denotes a playing card in the Student's Life game.
 */
public interface Card {
	
	/**
	 * Get the name of the card.  Card names are not unique, but cannot 
	 * be null, an empty String or have any leading or trailing whitespace.
	 * 
	 * @return the name of the card
	 */
	public String getName();
	
	/**
	 * Returns the name of the card.
	 */
	public String toString();
	
	/**
	 * Gets the card in a HTML formatted string. When displayed, HTML should 
	 * consist of two lines, with the first line containing the cards name 
	 * in italics and the second line containing the card's ID.  Note that 
	 * all text should be centered.
	 */
	public String toHtmlString();
	
	/**
	 * Get the identifier for the card.  These identifiers are unique 
	 * within the context of a (full) deck of cards, but not necessarily
	 * unique between decks, or across the entire application.
	 * 
	 * @return the Id of the card
	 */
	public int getId();
	
	/**
	 * Gives the score for a particular card. The score of a card is determined
	 * by its type, as follows:
	 * <ul>
	 * <li>Graded Subject card - score 1</li>
	 * <li>Ungraded Subject card - score 0</li>
	 * <li>Freeze Subject Card card - score -1</li>
	 * <li>Freeze Player Card card - score -2</li>
	 * </ul>
	 * @return the score for the card
	 */
	public int getScore();
	
	/**
	 * Two cards are equal if they have the same id.  Refer to
	 * {@link Object#equals(Object)} for the full contract for 
	 * equals.
	 */
	@Override
	public boolean equals(Object other);
}
