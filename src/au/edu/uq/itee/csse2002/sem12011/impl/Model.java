package au.edu.uq.itee.csse2002.sem12011.impl;

import java.util.ArrayList;

import au.edu.uq.itee.csse2002.sem12011.*;
import au.edu.uq.itee.csse2002.sem12011.impl.*;

/**
 * 
 * 
 * Playing the Game
 * 	play card from hand
 * 		select card to play and select subject card to play on
 * 		select "start round"
 * 		if no card selected, player picks up card from deck (or skips turn)
 * 	pick up card from deck
 * 	place subject card
 * 	freeze/unfreeze subject card
 * 	grade unfrozen subject card
 * 
 * Rules
 * 
 * 
 * Model uses ActionListeners 
 * 	lots of If -> Then statements to check what information
 * 		 needs to be sent to viewer
 * 
 * Start Game
 * 	load deck from specified file
 * 	shuffle deck
 * 	deal cards from top of deck
 * 		user gets first card
 * 		deal to each player in turn
 * 
 * */

public class Model{
	ArrayList<AbstractPlayer> players;
	
	
	//shuffle deck
	public void shuffleDeck() {
		players.get(0).deck.shuffle();
	}
	
	/**
	 * Creates an ArrayList of players. This includes the user's player and
	 * three opponents.
	 * If the ArrayList already contains players, throw a StudentsLifeException.
	 * @return an ArrayList<AbstractPlayers> that contains the players
	 * @throws StudentsLifeException 
	 */
	public void createPlayers(Deck deck) throws StudentsLifeException{
		GUIPlayer user = new GUIPlayer(deck);
		VirtualPlayer opponent1 = new VirtualPlayer(deck);
		VirtualPlayer opponent2 = new VirtualPlayer(deck);
		VirtualPlayer opponent3 = new VirtualPlayer(deck);
		
		players.add(user);
		players.add(opponent1);
		players.add(opponent2);
		players.add(opponent3);
	}
	
	
	//deal first hand
	public void dealFirstHand() throws BadDeckOperationException{
		boolean keepDealing = true;
		while (keepDealing) {
			for (AbstractPlayer player: this.players){
				if (player.deck.size() == 0)
					throw new BadDeckOperationException("no more cards in deck");
				
				player.pickUp();
				if (player.hand.size() == 5)
					keepDealing = false;
			}
		}	
	}
	
	public void playerMove(int inHandIndex, int inPlayIndex) {
		Card playerCard = players.get(0).hand.get(inHandIndex);
		if (playerCard instanceof SubjectCard) {
			players.get(0).inPlay.add((SubjectCard) playerCard); 
		}
		
		int cardOwner = this.getCoordinates(inPlayIndex).get(0);
		int cardIndex = this.getCoordinates(inPlayIndex).get(1);
		SubjectCard inPlayCard = players.get(cardOwner).inPlay.get(cardIndex);
		
		if (playerCard instanceof FreezeSubjectCard) {
			FreezeSubjectCard freezeSubjectCard = 
				(FreezeSubjectCardImpl) playerCard;
			
			if (freezeSubjectCard.getType().equals(
					FreezeSubjectCard.FreezeSubjectCardType.NORMAL)) {
				players.get(cardOwner).inPlay.get(cardIndex).
				freezeWith((FreezeSubjectCard) playerCard);
			}
			
			if (freezeSubjectCard.getType().equals(
					FreezeSubjectCard.FreezeSubjectCardType.GRADE)) {
				players.get(cardOwner).inPlay.get(cardIndex).
				gradeWith((FreezeSubjectCard) playerCard);
			}
		}
		players.get(0).hand.remove(inHandIndex);
	}
	
	public ArrayList<Integer> getCoordinates(int inPlayIndex){
		// TODO COMMENT
		ArrayList<Integer> coordinates = new ArrayList<Integer>(2);
		// Gets the owner and the position of the card being targeted (position meaning it's position within
		// the ArrayList which holds all the inPlay )
		int cardOwner = -1;
		int cardIndex = -1;
		int iterateCount = 0;
		for (int player = 0; player < players.size(); player++) {
			for (int position = 0; position < players.get(player).inPlay.size();
			position++) {
				iterateCount++;
				if (iterateCount == inPlayIndex) {
					cardOwner = player;
					cardIndex = position;
				}
			}
		} 
		coordinates.add(cardOwner);
		coordinates.add(cardIndex);
		return coordinates;
	}
	
	
//	// puts a subject card in play
//	// removes from hand
//	public void playSubjectCard(int inHandIndex){
//		SubjectCard card = (SubjectCard) players.get(0).hand.get(inHandIndex);
//		players.get(0).inPlay.add(card);
//	}
	
	
//	// a subject card in play will be frozen
//	// a freeze_subject_card will be removed from a players hand 
//	public void playFreezeSubjectCard(int inHandIndex, int inPlayIndex){
//	int cardOwner = this.getCoordinates(inPlayIndex).get(0);
//	int cardIndex = this.getCoordinates(inPlayIndex).get(1);
//	
//	
//	
//	
//	
//	}
	
}
