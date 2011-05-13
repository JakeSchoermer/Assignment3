package au.edu.uq.itee.csse2002.sem12011;

/**
 * A bonus card performs a special action, as defined by the type.
 */
public interface BonusCard extends Card {
	
	/**
	 * The supported types of bonus card.
	 */
	public enum BonusCardType {
		/** Steal a card from one player */
		STEAL_ONE_PLAYER,
		/** Steal a card from all players */
		STEAL_ALL_PLAYERS,
		/** Cause a player to lose a turn */
		UNFREEZE_PLAYER,
		/** Stops a subject from being awarded a grade */
		UNFREEZE_SUBJECT
	}
	
	/**
	 * Getter method for the type of this card.
	 * 
	 * @return the type of this card
	 */
	public BonusCardType getType();

}
