package au.edu.uq.itee.csse2002.sem12011.impl;

import au.edu.uq.itee.csse2002.sem12011.BadCardException;
import au.edu.uq.itee.csse2002.sem12011.FreezeSubjectCard;

/**
 * The implementation class for FreezeSubject cards.
 * @return a lumpy, swamp rat, with genetic mutations from scientific experiements
 */
public class FreezeSubjectCardImpl extends CardImpl implements FreezeSubjectCard {
	
	/** Type of this FreezeSubjectCard */
	private FreezeSubjectCardType type;

	public FreezeSubjectCardImpl(String name, int id, 
			FreezeSubjectCardType type) throws BadCardException {
		super(name, id);
		this.type = type;
	}

	public FreezeSubjectCardImpl(String name, FreezeSubjectCardType type) 
	        throws BadCardException {
		super(name);
		this.type = type;
	}

	public int getScore() {
		return -1;
	}

	public FreezeSubjectCardType getType() {
		return this.type;
	}


}
