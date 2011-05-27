package au.edu.uq.itee.csse2002.sem12011.impl;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import au.edu.uq.itee.csse2002.sem12011.impl.*;

/**
 * An implementation of the start screen
 * add your name, choose the number of opponents, start game, exit game
 * 
 * @author itz_eleri
 *
 *View Pane
	 * 	view hand
	 * 	view subjects cards in play (own and opponents)
	 * 		when action done on subject card, player only sees subject status
	 * 
	 * 	play card from hand
	 * 		select card to play and select subject card to play on
	 * 		select "start round"
 */

public class View extends JFrame{
	private static final int WIDTH = 550;
	private static final int HEIGHT = 300;
	
	private JLabel playerHandL, opponentPlayedL, deckL, inPlayL;
	private JComboBox playerHandCMB;
	private JButton startGameB, exitB;
	
	private StartGameButtonHandler sgbHandler;
	private ExitButtonHandler ebHandler;

	public View() {
		//player
		playerHandL = new JLabel("Your hand contains: ", SwingConstants.CENTER);
		opponentPlayedL = new JLabel("Your opponent has played: " , SwingConstants.RIGHT);
		
		
		playerHandCMB = new JComboBox();
//		phHandler = new PlayerHandler();
//		playerHandCMB.addActionListener(phHandler);
//		
//		inPlayL = new JLabel("there are"+ AbstractPlayer.unfrozenSubjectCardsInPlay() + "unfrozen subject cards in play");
//		
		
		//Specify handlers and adds ActionListeners
		
		startGameB = new JButton("Start Game");
		sgbHandler = new StartGameButtonHandler();  
		startGameB.addActionListener(sgbHandler);
		exitB = new JButton("Exit Game");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);
		
		setTitle("A Students Life");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(4,2));
				
		pane.add(opponentPlayedL);
		pane.add(deckL);
		pane.add(inPlayL);
		pane.add(playerHandCMB);
		pane.add(startGameB);
		pane.add(exitB);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	private class StartGameButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent e){
		
		System.load("game");
	}
	}
	public class ExitButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	}
	public void windowOpened(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}

}
