package au.edu.uq.itee.csse2002.sem12011;

/**
 * A FreezeSubject freezes a Subject card.
 */
public interface FreezeSubjectCard extends Card {
	
	/**
	 * The types of freeze card
	 */
	public enum FreezeSubjectCardType {
		/** This represents awarding of a grade for a subject */
		GRADE,
		/** This blocks awarding of a grade ... until unfrozen. */
		NORMAL
	}
	
	/**
	 * Get the type of this card.
	 * 
	 * @return the type of this card
	 */
	public FreezeSubjectCardType getType();

}
