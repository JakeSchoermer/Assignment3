package au.edu.uq.itee.csse2002.sem12011.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import au.edu.uq.itee.csse2002.sem12011.BadDeckException;
import au.edu.uq.itee.csse2002.sem12011.Card;
import au.edu.uq.itee.csse2002.sem12011.Deck;

/**
 * This class implements the Deck API using a Deque to hold the
 * cards in the deck.
 */
public class DeckImpl implements Deck {

	/** Cards currently in the deck */
	private ArrayDeque<Card> cards;
	
	/** Number of cards in the deck when we started */
	private final int fullSize;
	
	/**
	 * Construct a deck consisting of the cards in the supplied array.
	 * The cards must be non-null and must have different id values.
	 * 
	 * @param initialDeck the initial cards.
	 * @throws BadDeckException this is thrown if the initial cards
	 *     don't meet the criteria above.
	 */
	public DeckImpl(Card... initialDeck) throws BadDeckException {
		cards = new ArrayDeque<Card>();
		for (Card card : initialDeck) {
			for (Card existingCard : cards) {
				if (existingCard.getId() == card.getId()) {
					throw new BadDeckException("card appears more than once");
				}
			}
			cards.add(card);
		}
		fullSize = cards.size();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void shuffle() {
		ArrayList<Card> list = new ArrayList<Card>(cards);
		Collections.shuffle(list);
		cards = new ArrayDeque<Card>(list);
	}

	public Card pickUp() {
		return cards.poll();
	}

	public void putBack(Card c) {
		cards.add(c);
	}

	public int size() {
		return cards.size();
	}
	
	public int fullSize() {
		return fullSize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card c: cards) {
			builder.append(c.getId() + ",");
		}
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
}
