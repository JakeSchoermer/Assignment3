package au.edu.uq.itee.csse2002.sem12011.impl;

import au.edu.uq.itee.csse2002.sem12011.BadCardException;
import au.edu.uq.itee.csse2002.sem12011.Card;

/**
 * This class implements the Card interface.
 * <p>
 * Note that instances of CardImpl (or more accurately, instances of
 * concrete subclasses of CardImpl) are not guaranteed to have ids that 
 * are unique.  In particular, if cards are recreated, they may have ids
 * that are shared with other cards,
 */
public abstract class CardImpl implements Card {
	
	private static int nextId;
	
	/**
	 * Get the next unique id. 
	 * @return the id
	 */
	private static int getNextId() {
		return nextId++;
	}
	
	/** the name of the card */
	private final String name;
	
	/** the ID of the card */
	private final int id;
	
	/**
	 * Recreate a card with a given name and id.
	 * 
	 * @param name the existing card's name
	 * @param id the existing card's id
	 * @throws BadCardException if the card name is invalid. 
	 */
	public CardImpl(String name, int id) throws BadCardException {
		if (name == null) {
			throw new BadCardException("name is null");
		}
		if (name.isEmpty()) {
			throw new BadCardException("name is empty");
		}
		if (!name.equals(name.trim())) {
			throw new BadCardException("name has leading or trailing whitespace");
		}
		this.name = name;
		this.id = id;
	}
	
	/**
	 * Create a new card with a given name and a new id.
	 * 
	 * @param name the existing card's name
	 * @throws BadCardException if the card name is invalid. 
	 */
	public CardImpl(String name) throws BadCardException {
		this(name, getNextId());
	}
	
	public final String getName() {
		return this.name;
	}
	
	public final int getId() {
		return this.id;
	}
	
	public String toString() {
		return getName();
	}
	
	public String toHtmlString() {
		// (Note: the use of a StringBuilder is optional in this case.
		//  It turns out that the Java compiler will generate almost 
		//  identical code if you use one big String concatenation
		//  expression.)
		StringBuilder sb = new StringBuilder();
		sb.append("<font size=2 face=\"sanserif\">");
		sb.append("<center><i>").append(getName()).append("</i></center>");
		sb.append("<br />");
		sb.append("<center>").append(getId()).append("</center>");
		sb.append("</font>");
		return sb.toString();
	}
	
	//
	// Generated hashCode and equals methods using 'id'
	//

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardImpl other = (CardImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
