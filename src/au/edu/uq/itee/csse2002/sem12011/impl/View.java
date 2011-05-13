package au.edu.uq.itee.csse2002.sem12011.impl;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class View extends JFrame{
private static final int WIDTH = 400;
private static final int HEIGHT = 300;
private JLabel lengthL, widthL, areaL;
private JTextField lengthTF, widthTF, areaTF;
private JButton calculateB, exitB;
//Button handlers:
private CalculateButtonHandler cbHandler;
private ExitButtonHandler ebHandler;
public View() {
lengthL = new JLabel("Enter the length: ", SwingConstants.RIGHT);
widthL = new JLabel("Enter the width: ", SwingConstants.RIGHT);
areaL = new JLabel("Area: ", SwingConstants.RIGHT);
lengthTF = new JTextField(10);
widthTF = new JTextField(10);
areaTF = new JTextField(10);
//SPecify handlers for each button and add (register) ActionListeners to each button.
calculateB = new JButton("Calculate");
cbHandler = new CalculateButtonHandler();
calculateB.addActionListener(cbHandler);
exitB = new JButton("Exit");
ebHandler = new ExitButtonHandler();
exitB.addActionListener(ebHandler);
setTitle("A Students Life");
Container pane = getContentPane();
pane.setLayout(new GridLayout(4, 2));
//Add things to the pane in the order you want them to appear (left to right, top to bottom)  
pane.add(lengthL);
pane.add(lengthTF);
pane.add(widthL);
pane.add(widthTF);
pane.add(areaL);
pane.add(areaTF);
pane.add(calculateB);
pane.add(exitB);
setSize(WIDTH, HEIGHT);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
private class CalculateButtonHandler implements ActionListener{
public void actionPerformed(ActionEvent e){
double width, length, area;
length = Double.parseDouble(lengthTF.getText()); //We use the getText & setText methods to manipulate the data entered into those fields.
width = Double.parseDouble(widthTF.getText());
area = length * width;
areaTF.setText("" + area);
}
}
public class ExitButtonHandler implements ActionListener {
public void actionPerformed(ActionEvent e) {
	System.exit(0);
}
}

}
