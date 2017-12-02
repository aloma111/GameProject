/**
 * 
 * Author: Mohammed Alsoheem
 * Student ID: 2283736
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: GameClientHandler handles the player connection
 * It parses command, process and responses to the client
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class GameClientHandler extends Thread{
	
	/**
	 * reference to socket
	 */
	private Socket socket;
	
	/**
	 * a reference to game server
	 */
	private GameServer gameServer;
	
	/**
	 * constructor method
	 * 
	 * @param socket reference to socket that get from GameServer
	 */
	public GameClientHandler(GameServer gameServer, Socket socket) {
		this.gameServer = gameServer;
		this.socket = socket;
	}

	/**
	 * run method, this method is called by start method
	 * The method parses command, process and responses to the client
	 */
	public void run(){
		
		//reader
		BufferedReader in;
		//writer
		PrintStream out;
		try {
			//create BufferedReader and PrintStream
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			
			//read a line and print it
			//this is debug line, the line will be processed later
			String message = in.readLine();
			out.println("Got message: " + message);
			
			//print message
			gameServer.getServerGUI().printMessage("Got message: " + message);
			
			//close resources
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			//print to standard output any error message
			e.printStackTrace();
		}
		
	}
}
