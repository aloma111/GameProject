/**
 * 
 * Author: Mohammed Alsoheem
 * Student ID: 2283736
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: GameClientHandler handles the player connection
 * It parses command, process and responses to the client
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	 * current score
	 */
	private int score = 0;
	
	/**
	 * input
	 */
	private ObjectInputStream in;
	
	/**
	 * output
	 */
	private ObjectOutputStream out;
	
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
		
		
		
		//message
		Message msg = null;
		
		try {
			//create ObjectOutputStream and ObjectInputStream
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());			
			
			int gameState = 1;
			
			while (gameState != 3){
				
				if (gameState == 1){
					
					//send current information to client
					msg = gameServer.createMessage(score);
					
					synchronized (out) {
						out.writeObject(msg);
						out.notifyAll();
					}				
					
					//send the question to client
					msg = new Message();
					msg.setQuestion(gameServer.getCurrentQuestion());
					
					synchronized (out) {
						out.writeObject(msg);
						out.notifyAll();
					}
					
					
					gameState = 2;
					
				}else if (gameState == 2){
					
					//read answer from client
					//note that if the client has answer (any answer from server), it will move to state 1
					msg = (Message)in.readObject();
					
					//process answer					
					gameServer.doAnswer(this, msg.getAnswer());
					gameState = 1;
					
				}
				
				//EXIT ?
			}
			
			//close resources
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			//client exit			
		} catch (ClassNotFoundException e){
			//not here
		}
		
	}
	
	/**
	 * accessor method of score
	 * @return score score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * mutator method of score
	 * @param score score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * send message to client
	 * @param msg message
	 */
	public void sendMessge(Message msg){
		synchronized (out) {
			try {
				out.writeObject(msg);
			} catch (IOException e) {
				// error, simply print to console
				e.printStackTrace();
			}
			out.notifyAll();
		}
	}
}
