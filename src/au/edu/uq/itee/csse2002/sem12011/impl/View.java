package au.edu.uq.itee.csse2002.sem12011.impl;

import java.applet.Applet;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;

/**
 * An implementation of the start screen
 * add your name, choose the number of opponents, start game, exit game
 * 
 * @author Jake Schoermer, Eleri Edwards, Angelo Chan
 *
 */

public class View extends JFrame{
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 600;
	
	private JLabel welcomeL, newPlayerL, numberOfOpponentsL, testL;
	private JLabel Opponent1NameL, Opponent2NameL, Opponent3NameL, HumanNameL;
	private JList Opp1CardsinPlayList;
	private JTextField newPlayerTF;
	private JButton startGameBtn, exitBtn;
	private JRadioButton oneOpponentRB;
	private JRadioButton twoOpponentsRB;
	private JRadioButton threeOpponentsRB;
	
	private StartGameButtonHandler sgbHandler;
	private ExitButtonHandler ebHandler;
	
	private Border EmptyBorder = BorderFactory.createEmptyBorder(5,5,5,5);
	
	public View() {	
		
		Container titleGrid = new JPanel();
		titleGrid.setBackground(Color.blue);
		titleGrid.setBounds(0, 0, WIDTH, 50);
		
		welcomeL = new JLabel("Welcome to 'A Student's Life'", SwingConstants.CENTER);
		welcomeL.setFont(new Font("Harrington" , Font.PLAIN, 24));
		welcomeL.setForeground(Color.white);
		welcomeL.setOpaque(false);
		
		titleGrid.add(welcomeL);
		
		
		Container PlayerGrid = new JPanel();
		PlayerGrid.setLayout(new GridLayout(2,2));
		PlayerGrid.setBackground(Color.RED);
		PlayerGrid.setBounds(0, 50, WIDTH, HEIGHT-150);
		
		
		
				//Opponent1
				Container Opponent1Grid = new JPanel();
				Opponent1Grid.setLayout(new FlowLayout());
					Opponent1NameL = new JLabel("Opponent 1", SwingConstants.LEFT);
					String[] CardNames = {"Card 1, Card 2, Card 3"};	
					Opp1CardsinPlayList = new JList(CardNames);
								
				//Opponent2
				Opponent2NameL = new JLabel("Opponent 2", SwingConstants.LEFT);
				
				//Opponent3
				Opponent3NameL = new JLabel("Opponent 3", SwingConstants.LEFT);
				
				//Human
				HumanNameL = new JLabel("Human", SwingConstants.LEFT);
				
				
						
				PlayerGrid.add(Opponent1Grid);
				Opponent1Grid.add(Opponent1NameL);
				Opponent1Grid.add(Opp1CardsinPlayList);
				
				PlayerGrid.add(Opponent2NameL);
				PlayerGrid.add(Opponent3NameL);
				PlayerGrid.add(HumanNameL);

		//Specify handlers and adds ActionListeners to start and exit buttons.
		startGameBtn = new JButton("Start Game");
		
		
		sgbHandler = new StartGameButtonHandler();  
		startGameBtn.addActionListener(sgbHandler);
		
		exitBtn = new JButton("Exit Game");
		ebHandler = new ExitButtonHandler();
		exitBtn.addActionListener(ebHandler);
		
				
		//ControlPanel		
		Container ControlPanelGrid = new JPanel();
		ControlPanelGrid.setLayout(new FlowLayout());
		ControlPanelGrid.setBackground(Color.GREEN);
		ControlPanelGrid.setBounds(0, 100, WIDTH, 100);
		
		
		ControlPanelGrid.add(startGameBtn, BorderLayout.WEST);
		ControlPanelGrid.add(exitBtn);

		
		newPlayerL = new JLabel("Enter your name: ", SwingConstants.RIGHT);
		newPlayerTF = new JTextField(10);
		numberOfOpponentsL = new JLabel("Choose your number of opponents: ", 
				SwingConstants.CENTER);
		
		

		
		
				

		
		setTitle("A Students Life");
		
		Container pane = this.getContentPane();
		
		
		//Instantiate GUI Elements
		pane.add (titleGrid, BorderLayout.NORTH);	
		pane.add(PlayerGrid, BorderLayout.CENTER);
		pane.add(ControlPanelGrid, BorderLayout.SOUTH);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		
		
	}
	
	private class StartGameButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent e){
	//TODO
		//stores name of player
//		if(newPlayerTF != null){
//			AbstractPlayer.addOpponent().name(newPlayerTF);
//		}
//		//creates selected number of opponents
//		if(oneOpponentRB.isSelected()){
//			AbstractPlayer.addOpponent().name(opponent1);
//		}else if (twoOpponentsRB.isSelected()){
//			AbstractPlayer.addOpponent().name(opponent1);
//			AbstractPlayer.addOpponent().name(opponent2);
//		}else if(threeOpponentsRB.isSelected()){
//			AbstractPlayer.addOpponent().name(opponent1);
//			AbstractPlayer.addOpponent().name(opponent2);
//			AbstractPlayer.addOpponent().name(opponent3);
//		}	
//			
			
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
