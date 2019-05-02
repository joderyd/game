package lab3;
import java.net.*;
import java.io.*;
import java.util.*;

public class Kladd{
	public static void main(String[] args){

try {
       //Socket socket=new Socket("gru-ld03.csc.kth.se",4713);
	Socket socket=new Socket("localhost",4713);
       	BufferedReader in=new BufferedReader(
	    new InputStreamReader(socket.getInputStream()));
       	PrintWriter ut=new PrintWriter(socket.getOutputStream());
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

       	ut.println("Jonathan"); 
	ut.flush();
	while(true){
	String srv = in.readLine();
	System.out.println("Server: " + srv);
	String usr = stdIn.readLine();
	ut.println(usr);
	ut.flush();}
	}
catch(IOException e){System.out.println(e);}
	}
}

