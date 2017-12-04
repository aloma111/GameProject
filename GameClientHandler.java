/**
 * 
 * Author: Moath Alomar
 * Student ID: 2283736
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: GameClientHandler handles the player connection
 * It parses command, process and responses to the client
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		
		//input
		ObjectInputStream in;
		//output
		ObjectOutputStream out;
		try {
			//create ObjectOutputStream and ObjectInputStream
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());			
			
			//send a sample question to client
			Message msg = new Message();
			msg.setQuestion(gameServer.getCurrentQuestion());
			out.writeObject(msg);
			
			//close resources
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			//client exit
			
		}
		
	}
}
