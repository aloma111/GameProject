
/**
 * 
 * Author: Moath Alomar
 * Student ID: 2283736
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: Game client open a secure connection to the TCP secure socket server
 * read the command and send to server
 * Any response from server will be printed on standard output.
 * This response is handled by a separated thread
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

public class GameClient extends Thread{

	/**
	 * server host
	 */
	private static String SERVER_HOST = "127.0.0.1";

	/**
	 * server port
	 */
	private static int SERVER_PORT = 1234;
	
	/**
	 * reference to GUI to display message
	 */
	private ClientGUI clientGUI;
	
	/**
	 * constructor
	 * 
	 * @param clientGUI reference to GUI
	 */
	public GameClient(ClientGUI clientGUI) {
		this.clientGUI = clientGUI;
	}

	/**
	 * run method, this method is called by start method The method read command
	 * from user and send to the server
	 */
	public void run() {

		//create SSLSocketFactory object with store
		System.setProperty("javax.net.ssl.trustStore", Configuration.STORE_NAME);
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

		try {
			//create socket
			Socket socket = sf.createSocket(SERVER_HOST, SERVER_PORT);

			// write some words
			OutputStream out = socket.getOutputStream();
			out.write("hello\n".getBytes());
			out.flush();

			//read a line and simply print on standard output
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = in.readLine();
			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}

			clientGUI.printMessage("Client is running");
			
			//close resource
			out.close();
			socket.close();

		} catch (UnknownHostException e) {
			clientGUI.printMessage(e.getMessage());
		} catch (IOException e) {
			clientGUI.printMessage(e.getMessage());
		}
	}

	

}
