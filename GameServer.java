/**
 * 
 * Author: Mohammed Alsoheem
 * Student ID: 2283736
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The GameServer class is TCP multi-threading server
 * that accepts secure TCP connection.
 * The server create a thread for each connection
 * Each thread handles a player. The thread parses command, process
 * and return TCP packet to client.
 */

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;

import question.Question;
/**
 * Game Server
 */
public class GameServer extends Thread{

	/**
	 * server port
	 */
	private static int SERVER_PORT = 1234;
	
	/**
	 * list of GameClientHandler object
	 */
	private List<GameClientHandler> clientHandlers;
	
	/**
	 * reference to GUI to display message
	 */
	private ServerGUI serverGUI;
	
	/**
	 * question generator
	 */
	private QuestionGenerator questionGenerator;
	
	/**
	 * constructor
	 * initialize the array list
	 */
	public GameServer(ServerGUI serverGUI){
		clientHandlers = new ArrayList<>();
		this.serverGUI = serverGUI;
		
		//create unique instance of question generator
		questionGenerator = QuestionGenerator.instance();
		
		//TODO, create sample
		question = questionGenerator.getQuestion();
	}
	
	/**
	 * current question
	 */
	private Question question = null;
	
	/**
	 * get current question
	 * @return current question
	 */
	public Question getCurrentQuestion(){
		
		//TODO - Test only
		for (int i = 0; i < (int)(Math.random() * 4); i++){
			question = questionGenerator.getQuestion();
		}
		
		return question;
	}
	
	/**
	 * run the sever
	 * @throws Exception if there is error in key store
	 */
	public void run(){
		
		try{
			
			//create SSLServerSocketFactory object that load from key store
			//SSL is TLS, SunX509
			SSLContext context;
		    KeyManagerFactory kmf;
		    KeyStore ks;

		    context = SSLContext.getInstance("TLS");
		    kmf = KeyManagerFactory.getInstance("SunX509");
		    FileInputStream fin = new FileInputStream(Configuration.STORE_NAME);
		    ks = KeyStore.getInstance("JKS");
		    ks.load(fin, Configuration.STORE_PASS.toCharArray());

		    kmf.init(ks, Configuration.KEY_PASS.toCharArray());
		    context.init(kmf.getKeyManagers(), null, null);
		    SSLServerSocketFactory ssf = context.getServerSocketFactory();
		    
		    //create Server socket from 
		    ServerSocket ss = ssf.createServerSocket(SERVER_PORT);
		    
		    //loop to wait for client connection
		    //if there is connection, the server open new thread
		    //the thread will handles this connection
		    while (true) {
		      
		    	//accept connection
		    	Socket s = ss.accept();
		      
		    	//add GameClientHandler object, add to list and start it as thread
		    	GameClientHandler clientHandler = new GameClientHandler(this, s);
		    	clientHandlers.add(clientHandler);
		    	clientHandler.start();
		    }
		    
		}catch(Exception e){
			
			//any exception, simply print to standard output
			serverGUI.printMessage(e.getMessage());
		}
	}

	/**
	 * get reference to server GUI
	 * @return server GUI
	 */
	public ServerGUI getServerGUI() {
		return serverGUI;
	}	
	
	
}
