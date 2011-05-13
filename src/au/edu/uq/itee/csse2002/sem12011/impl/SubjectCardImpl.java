package au.edu.uq.itee.csse2002.sem12011.impl;

import au.edu.uq.itee.csse2002.sem12011.BadCardException;
import au.edu.uq.itee.csse2002.sem12011.FreezeSubjectCard;
import au.edu.uq.itee.csse2002.sem12011.InvalidActionException;
import au.edu.uq.itee.csse2002.sem12011.SubjectCard;

/**
 * The implementation class for Subject cards.
 */
public class SubjectCardImpl extends CardImpl implements SubjectCard {

	/* the card currently afflicting this one */
	private FreezeSubjectCard cardFrozenBy;

	/* whether or not the card is graded */
	private boolean isGraded;

	/**
	 * Create a subject card in the default initial state; not
	 * graded and not frozen.  The card will have a new id.
	 * 
	 * @param name the 
	 * @throws BadCardException
	 */
	public SubjectCardImpl(String name) throws BadCardException {
		super(name);
		cardFrozenBy = null;
		isGraded = false;
	}

	public SubjectCardImpl(String name, int id) throws BadCardException {
		super(name, id);
		cardFrozenBy = null;
		isGraded = false;
	}

	public FreezeSubjectCard getCardFrozenBy() {
		return cardFrozenBy;
	}

	public void freezeWith(FreezeSubjectCard card) throws InvalidActionException {
		if (isFrozen()) {
			throw new InvalidActionException("Subject is already frozen");
		}
		if (isGraded) {
			throw new InvalidActionException("Subject is graded.");
		}
		cardFrozenBy = card;
	}

	public void gradeWith(FreezeSubjectCard card) throws InvalidActionException {
		if (isFrozen()) {
			throw new InvalidActionException("Subject is frozen");
		}
		if (isGraded) {
			throw new InvalidActionException("Subject is already graded.");
		}
		cardFrozenBy = card;
		isGraded = true;
	}

	public void unfreeze() throws InvalidActionException {
		if (!isFrozen() || isGraded)
			throw new InvalidActionException("Subject is not already frozen.");
		cardFrozenBy = null;
	}

	public boolean isFrozen() {
		return (cardFrozenBy != null);
	}

	public boolean isGraded() {
		return isGraded;
	}

	@Override
	public int getScore() {
		return isGraded ? 1 : 0;
	}
	
	public String toHtmlString() {
		StringBuilder builder = new StringBuilder();
		if (this.isGraded) {
			builder.append("<font color=green size=3 face=\"sanserif\"><center>A+</center></font>");
		} else if (this.isFrozen()) {
			builder.append("<font color=blue size=3 face=\"sanserif\"><center>*</center></font>");
		}
		builder.append(super.toHtmlString());
		return builder.toString();
	}

}
