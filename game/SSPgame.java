// Jonathan Öderyd, Labb3, 2017-11-12, Programutvecklingsteknik, CMETE3
package lab3;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

class Clistener implements ActionListener{
public void actionPerformed(ActionEvent E){
	System.exit(0);}
}



class SSPgame extends JFrame implements ActionListener{
    Gameboard myboard, computersboard;
    int counter; // To count ONE ... TWO  and on THREE you play
    BufferedReader in;
    PrintWriter ut;
    Socket socket;
    JButton closebutton;
    static String hand, chand;
    Clistener lyssnare=new Clistener();

    SSPgame () {
	super("Rock-Paper-Scissors");
	counter = 0;
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	closebutton = new JButton("Close");
	closebutton.addActionListener(lyssnare);
	myboard = new Gameboard("Myself", this); // Must be changed
	computersboard = new Gameboard("Computer");
	JPanel boards = new JPanel();
	boards.setLayout(new GridLayout(1,2));
	boards.add(myboard);
	boards.add(computersboard);
	add(boards, BorderLayout.CENTER);
	add(closebutton, BorderLayout.SOUTH);
	setSize(300, 550);
	setVisible(true);

	try { 
       //socket=new Socket("gru-ld03.csc.kth.se",4713);
	socket=new Socket("localhost",4713);
        in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	ut=new PrintWriter(socket.getOutputStream());
	ut.println("Jonathan"); 
	ut.flush();
	System.out.println("Server: " + in.readLine());
        }
	catch(IOException e){System.out.println("fel...");}
    }//konstruktor slut


public String beats(String phand){
	String start = "I";
	if(phand == "STEN"){start= "SAX";}
	if(phand == "SAX"){start= "PASE";} 
	if(phand == "PASE"){start= "STEN";}
	return start;}

    

public void actionPerformed(ActionEvent E){
    try{	
	myboard.resetColor();
	computersboard.resetColor();
	hand = E.getActionCommand();
	ut.println(hand);
	ut.flush();
	chand = in.readLine();
	
	if(counter<3){	
	    counter = counter+1;
	    myboard.countHand(String.valueOf(counter));
	    computersboard.countHand(String.valueOf(counter));}

	if(counter==3){
	    counter = 0;
	    myboard.select(hand);
	    computersboard.select(chand);

	    if(chand.equals(hand)){;
	        myboard.countHand("Tie!");
		computersboard.countHand("Tie!");}

	    else if(chand.equals(beats(hand))){
		System.out.println("Winner winner chicken dinner!");
		myboard.win();
		computersboard.countHand("Loser");}

	    else{
		System.out.println("DU TORSKA!");
		computersboard.win();
		myboard.countHand("Loser");}
	}

	    //System.out.println(counter + " " + hand);
	    //System.out.println("Server: " + chand);
    }

    catch(IOException a){System.out.println(a);}
}//actionperformed ends



public static void main (String[] u) {	
    SSPgame SSP = new SSPgame();}
	
}

