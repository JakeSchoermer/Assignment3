package au.edu.uq.itee.csse2002.sem12011.impl;

import au.edu.uq.itee.csse2002.sem12011.BadCardException;
import au.edu.uq.itee.csse2002.sem12011.FreezePlayerCard;

/**
 * The implementation class for FreezePlayer cards.
 */
public class FreezePlayerCardImpl extends CardImpl implements FreezePlayerCard {

	public FreezePlayerCardImpl(String name, int id) throws BadCardException {
		super(name, id);
	}

	public FreezePlayerCardImpl(String name) throws BadCardException {
		super(name);
	}

	public int getScore() {
		return -2;
	}

}
