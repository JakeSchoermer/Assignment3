package au.edu.uq.itee.csse2002.sem12011;

import java.util.Iterator;

/** A Student's Life player. 
 *  @author Graeme Smith
 */
public interface Player {
	
	/** Add an opponent to the game the player is playing.
	 *  @param p -  the new opponent
	 */
	public void addOpponent(Player p);
	
	/** Receive a card and, if it is not null, place it in the 
	 *  player's hand.
	 *  @param card - the received card
	 */
	public void receiveCard(Card card);
	
	/** Take the player's next turn by doing one of the following:
	 *  (1) placing a subject card in play,
	 *  (2) freezing a subject card with a grade card,
	 *  (3) unfreezing a subject card with a bonus card
	 *  (4) freezing an opponent's subject card.
	 *  @throws UserException if the turn does not satisfy the rules
	 *  of the game
	 */
	public void play() throws UserException;
	
	/** Return the number of the player's unfrozen subject cards currently 
	 *  in play.
	 *  @return the number of unfrozen subject cards in play
	 */
	 public int unfrozenSubjectCardsInPlay();
	 
	 /** Pick up a card from the deck, if any, and place it in the
	  *  player's hand.
	  */
	 public void pickUp();
	 
	/** Return an iterator over the cards currently in the player's
	 *  hand.
	 *  @return the iterator
	 */
	 public Iterator<Card> getCardsInHandIterator();
	
	/** Return an iterator over the player's cards currently in
	 *  play.
	 *  @return the iterator
	 */
	public Iterator<SubjectCard> getCardsInPlayIterator();
}
