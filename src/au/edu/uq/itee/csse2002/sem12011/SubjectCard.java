package au.edu.uq.itee.csse2002.sem12011;

/**
 * This class is used to store data associated with a subject card and
 * provide a way to modify and access the state of the card. 
 * 
 * The following information must be stored:
 * 		- afflicted status (frozen/unfrozen)
 * 		- graded status (graded/ungraded)
 * 
 * A card can be frozen but not graded; a graded card must always be frozen.
 */
public interface SubjectCard extends Card {

	/**
	 * Getter method for the card this card is being frozen/graded by. A card
	 * is frozen/graded once another player places a FreezeSubjectCard on it.
	 * 
	 * @return the card afflicting this card, otherwise null
	 */
	public FreezeSubjectCard getCardFrozenBy();
	
	/**
	 * Sets this card to be frozen. Once the card is frozen, it must keep 
	 * track of the card that is afflicting it. A card may only be frozen
	 * by one other card at a time.
	 * 
	 * @throws InvalidMoveException if the card is already frozen or graded
	 * 
	 * @param card	the card to be set afflicting
	 */
	public void freezeWith(FreezeSubjectCard card) throws InvalidActionException;

	/**
	 * Sets this card to be graded. A card is graded once a grade card is 
	 * placed onto it.
	 * 
	 * @throws InvalidMoveException if the card has already been graded or
	 * it is frozen
	 */
	public void gradeWith(FreezeSubjectCard card) throws InvalidActionException;
	
	/**
	 * Sets this card to be unfrozen. Once a card is unfrozen, any
	 * afflictions are removed.
	 * 
	 * @throws InvalidMoveException if the card is not already frozen
	 * or it is graded 
	 */
	public void unfreeze() throws InvalidActionException;

	/**
	 * Getter method for the frozen status of this card.
	 * 
	 * @return true if the card has been frozen, false otherwise
	 */
	public boolean isFrozen();
	
	/**
	 * Getter method for the graded status of this card.
	 * 
	 * @return true if the card has been graded, false otherwise
	 */
	public boolean isGraded();
	
}
