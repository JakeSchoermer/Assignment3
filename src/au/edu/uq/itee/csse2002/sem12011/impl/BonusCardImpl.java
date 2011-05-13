package au.edu.uq.itee.csse2002.sem12011.impl;

import au.edu.uq.itee.csse2002.sem12011.BadCardException;
import au.edu.uq.itee.csse2002.sem12011.BonusCard;

/**
 * The implementation class for Bonus cards.
 */
public class BonusCardImpl extends CardImpl implements BonusCard {
	
	private BonusCardType type;

	public BonusCardImpl(String name, int id, BonusCard.BonusCardType type) 
	throws BadCardException {
		super(name, id);
		this.type = type;
	}
	
	public BonusCardImpl(String name, BonusCard.BonusCardType type) 
	throws BadCardException {
		super(name);
		this.type = type;
    }
	
	public BonusCardType getType() {
		return this.type;
	}

	public int getScore() {
		return 0;
	}
	
}
