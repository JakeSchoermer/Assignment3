package au.edu.uq.itee.csse2002.sem12011.impl;

import java.util.ArrayList;
import java.util.Iterator;

import au.edu.uq.itee.csse2002.sem12011.BonusCard;
import au.edu.uq.itee.csse2002.sem12011.Card;
import au.edu.uq.itee.csse2002.sem12011.Deck;
import au.edu.uq.itee.csse2002.sem12011.FreezeSubjectCard;
import au.edu.uq.itee.csse2002.sem12011.Player;
import au.edu.uq.itee.csse2002.sem12011.SubjectCard;

/** A virtual player controlled by the computer. 
 *  @author Graeme Smith
 */
public class VirtualPlayer extends AbstractPlayer {

	/** Create a new virtual player to play with a given deck
	 *  of cards.
	 *  @param deck - the deck
	 */
	 public VirtualPlayer(Deck deck) {
		hand = new ArrayList<Card>();
		inPlay = new ArrayList<SubjectCard>();
		opponents = new ArrayList<Player>();
		this. deck = deck;
	}
	
	 /** The player takes their turn by attempting in order 
	  *  (1) freezing an opponent's subject card,
	  *  (2) unfreezing a subject card with a bonus card,
	  *  (3) freezing a subject card with a grade card,
	  *  (4) placing a subject card in play,
	  *  and when all fail picking up a card from the deck.
	  */
	public void play() {
		if (freezeOpponentCard()) return;
		if (unfreezeCard()) return;
		if (gradeCard()) return;
		if (playSubjectCard()) return;
		pickUp();
	}
	
	/** Attempt to freeze an opponent's subject card by finding a freeze
	 *  subject card in the player's hand, and an unfrozen subject card 
	 *  of an opponent which is in play.
	 *  @return true when an opponent's subject card is frozen, and false
	 *  otherwise
	 */
	private boolean freezeOpponentCard() {
		Iterator<Card> hi = getCardsInHandIterator(); // cards in hand
		while (hi.hasNext()) {
			Card c = hi.next(); // next card in hand
			if (c instanceof FreezeSubjectCard &&
					((FreezeSubjectCard)c).getType().equals
						(FreezeSubjectCard.FreezeSubjectCardType.NORMAL)) {
				SubjectCard target = chooseTarget(); // opponent card to freeze
				if (target == null) {
					// no opponent's cards can be frozen
					return false;
				}
				target.freezeWith((FreezeSubjectCard)c);
				hand.remove(c);
				return true;
			}
		}
		// no freeze subject cards in player's hand
		return false;
	}
	
	/** Choose an opponent's subject card to freeze. A card from the 
	 *  an opponent with the maximum number of unfrozen cards in play 
	 *  is chosen.
	 *  @return the opponent's subject card
	 */
	private SubjectCard chooseTarget() {
		// maximum number of unfrozen cards a player has in play
		int maxCardsInPlay = 0; 
		Player opponent = null; // opponent to target
		for (Player p : opponents) {
			// the number of unfrozen cards p has in play
			int unfrozenCards = p.unfrozenSubjectCardsInPlay();
			if (unfrozenCards > maxCardsInPlay) {
				opponent = p;
				maxCardsInPlay = unfrozenCards;
			}
		}
		if (opponent == null) 
			// no opponent has unfrozen cards in play
			return null;
		// the cards in play of the chosen opponent
		Iterator<SubjectCard> pi = opponent.getCardsInPlayIterator();
		while (pi.hasNext()) {
			// the next card in play of the chosen opponent
			SubjectCard c = pi.next();
			if (! c.isFrozen())
				return c;				
		}	
		// no opponent's cards can be frozen
		return null;
	}
	
	/** Attempt to unfreeze a subject card by finding a bonus card
	 *  in the player's hand, and a frozen subject card of the 
	 *  player which is in play.
	 *  @return true when a card of the player is unfrozen, and false
	 *  therwise
	 */
	private boolean unfreezeCard() {
		Iterator<Card> hi = getCardsInHandIterator(); // cards in hand
		while (hi.hasNext()) {
			Card c = hi.next(); // next card in hand
			if (c instanceof BonusCard) {
				// cards in play
				Iterator<SubjectCard> pi = getCardsInPlayIterator();
				while (pi.hasNext()) {
					SubjectCard s = pi.next(); // next card in play
					if (s.isFrozen() && ! s.isGraded()) {
						s.unfreeze();
						hand.remove(c);
						return true;
					}
				}
				// no frozen cards of the player in play
				return false;
			}
		}
		// no bonus cards in the player's hand
		return false;
	}
	
	/** Attempt to grade a subject card by finding a grade card
	 *  in the player's hand, and an unfrozen and ungraded subject
	 *  card of the player's in play.
	 *  @return true when a card of the player is graded, and false
	 *  otherwise
	 */
	private boolean gradeCard() {
		Iterator<Card> hi = getCardsInHandIterator(); // cards in hand
		while (hi.hasNext()) {
			Card c = hi.next(); // next card in hand
			if (c instanceof FreezeSubjectCard && 
					((FreezeSubjectCard)c).getType().equals
						(FreezeSubjectCard.FreezeSubjectCardType.GRADE)) {
				// cards in play
				Iterator<SubjectCard> pi = getCardsInPlayIterator();
				while (pi.hasNext()) {
					SubjectCard s = pi.next(); // the next card in play
					if (! s.isFrozen() && ! s.isGraded()) {
						s.gradeWith((FreezeSubjectCard)c);
						hand.remove(c);
						return true;
					}
				}
				// no unfrozen and ungraded subject cards in play
				return false;
			}
		}
		// no grade cards in player's hand
		return false;
	}
	
	/** Attempt to place a subject card in play by finding a subject card
	 *  in the player's hand.
	 *  @return true when a subject card is placed in play, and false 
	 *  otherwise
	 */
	private boolean playSubjectCard() {
		Iterator<Card> hi = getCardsInHandIterator(); // cards in hand
		while (hi.hasNext()) {
			Card c = hi.next(); // next card in hand
			if (c instanceof SubjectCard) {
				hand.remove(c);
				inPlay.add((SubjectCard)c);
				return true;
			}
		}
		// no subject cards in the player's hand
		return false;
	}
	
}
