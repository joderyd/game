/*******************************************
OBS! No swedish letters in this program.
STEN, SAX and PASE is played.
STEN = ROCK, SAX = SCISSORS, PASE = PAPER
*******************************************/
package lab3;
import java.net.*;
import java.io.*;
import java.util.*;


public class Server4713 {
    public static void main( String[] args) {
	try {
	    ServerSocket sock = new ServerSocket(4713,100);    // Tries to create new serverSocket. port = 4713, max 100 users
	    while (true) 
		new ClientHandler(sock.accept()).start();      //  sock.accept = clientSocket
	}						       //  clientSocket.start() starts a new thread
	catch(IOException e)
	    {System.err.println(e);
	}
    }
} 

class ClientHandler extends Thread {
    static int antaltr=0;
    BufferedReader in;
    PrintWriter ut;
    public ClientHandler(Socket socket){
	try {
	    in = new BufferedReader(new InputStreamReader
				    (socket.getInputStream()));
	    ut= new PrintWriter(socket.getOutputStream());
	}
	catch(IOException e) {System.err.println(e);
	}
    }
    
    public void run() {
	Random random=new Random();
	String[] hand={"STEN","SAX","PASE"};
	try {
	    String namn=in.readLine();
	    System.out.println((++antaltr)+": "+namn);
            ut.println("Hej, "+namn);
            ut.flush();
	    while(true) {
		String indata = in.readLine();
		if(indata==null || indata.equals("")) break;
		ut.println(hand[random.nextInt(3)]);
		ut.flush();
	    }
	    System.out.println("Nu slutade "+namn);
	    antaltr--;
	}
        catch(Exception e) {
	    System.err.println(e);
	}
    }
}
