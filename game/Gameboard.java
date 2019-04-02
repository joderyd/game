package lab3;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Gameboard extends JPanel {

    private Icon[] icons = {new ImageIcon("rock.gif"), 
		    new ImageIcon("paper.gif"),
		    new ImageIcon("scissors.gif")};

    public JButton[] buttons = new JButton[3];
    private JButton lastPlayed; // remembers last chosen button/gesture
    private String[] texts = {"STEN", "PASE", "SAX"};
    private JLabel upperMess, lowerMess, scorelabel;
    private int score;
    private Color bgcolor;
    public HashMap<String,JButton> map = new HashMap<String,JButton>();


    // Constructor that builds the board, used for computers board
    Gameboard(String name) {
	this.score = 0;
	setLayout(new GridLayout(5,1));

	// Upper JPanel holds players name and last gesture played
	JPanel upper = new JPanel();
	upper.setLayout(new GridLayout(2,1));
	upper.add(new JLabel(name, JLabel.CENTER)); 
	upperMess = new JLabel("RPS", JLabel.CENTER);
	upper.add(upperMess);
	add(upper);

	// Lower JPanel has messages about the game and score
	JPanel lower = new JPanel();
	lower.setLayout(new GridLayout(2,1));
	lowerMess = new JLabel("win/lose/draw", JLabel.CENTER);
	scorelabel = new JLabel("Score: 0", JLabel.CENTER);
	lower.add(lowerMess); lower.add(scorelabel);

	for (int i = 0; i<3; i++){
	    buttons[i] = new JButton(texts[i],icons[i]);
	    buttons[i].setActionCommand(texts[i]);
	    add(buttons[i]);
	    // Store each button in a map with its text as key. 
            // Enables us to retrieve the button from a textvalue. 
	    map.put(texts[i],buttons[i]);
	}

	add(lower); // added after buttons
	bgcolor = buttons[0].getBackground();
	lastPlayed = buttons[0]; // arbitrary value at start
    }


    // Contructor for players board, puts listener on buttons
    Gameboard(String name, ActionListener listener) {
	this(name); // call other constructor to build the board
	for (int i = 0; i<3; i++)
	    buttons[i].addActionListener(listener);
    }


    void countHand(String n){
	lowerMess.setText(n);}

    void win(){
	score = score+1;
	scorelabel.setText("Score: " + String.valueOf(score));
	lowerMess.setText("Winner!");}


    void select(String Hand){
	JButton usr = map.get(Hand);
	usr.setBackground(Color.yellow);
	upperMess.setText(Hand);}

    // reset yellow color
    void resetColor() {
	buttons[0].setBackground(bgcolor);
	buttons[1].setBackground(bgcolor);
	buttons[2].setBackground(bgcolor);
	//lastPlayed.setBackground(bgcolor);
}
    }
