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
import java.io.OutputStream;

public class GameClientHandler extends Thread{
	
	/**
	 * reference to socket
	 */
	private Socket socket;
	
	/**
	 * constructor method
	 * 
	 * @param socket reference to socket that get from GameServer
	 */
	public GameClientHandler(Socket socket) {
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
			
			String name = in.readLine();
			System.out.println("Welcome " + name + ", the game will begin shortly");
			
			int state = 0;
			while(state < 4)
			{
				switch(state)
				{	
					case 0:
						//waiting for other players
						state = 1;
						break;

					case 1:
						//Sends Players The Question
					case 2:
						//Checks for Answers and Last Question
					case 3:
						//Last Question & Show Scores			
				}//switch
			}//while
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
