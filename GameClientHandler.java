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
			OutputStream out = socket.getOutputStream();
			
			//read a line and print it
 			//this is debug line, the line will be processed later
 			out.write("Got message: " + in.readLine().getBytes());
			
			int state = 0;
			while(state < 4)
			{
				switch(state)
				{	
					case 0:
						//read a line and print it
						//this is debug line, the line will be processed later
						out.write("Player Connected\n".getBytes());
						state = 1;
						break;

					case 1:
						//prints what the client types as long as it is not '~'
						//while(in.readLine().substring(0,1) != "~")
						//{
							String message = "From Player: " + in.readLine()"
							out.write((message+"\n").getBytes());
						//}
						
						state = 4;
						break;
					
					
						
						
						
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
