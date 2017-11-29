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
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
/**
 * Game Server
 */
public class GameServer {

	/**
	 * server port
	 */
	private static int SERVER_PORT = 1234;
	
	/**
	 * list of GameClientHandler object
	 */
	private List<GameClientHandler> clientHandlers;
	
	/**
	 * constructor
	 * initialize the array list
	 */
	public GameServer(){
		clientHandlers = new ArrayList<>();
	}
	
	/**
	 * run the sever
	 * @throws Exception if there is error in key store
	 */
	public void run() throws Exception{
		
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
		    	GameClientHandler clientHandler = new GameClientHandler(s);
		    	clientHandlers.add(clientHandler);
		    	clientHandler.start();
		    }
		    
		}catch(IOException e){
			
			//any exception, simply print to standard output
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * main method to start java application
	 * @param args the program argument
	 * @throws Exception if error
	 */
	public static void main(String[] args) throws Exception {
		//create Game server and call its run() method
		GameServer server = new GameServer();
		server.run();
	}

	
}
