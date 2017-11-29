
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

public class GameClient {

	/**
	 * server host
	 */
	private static String SERVER_HOST = "localhost";

	/**
	 * server port
	 */
	private static int SERVER_PORT = 1234;

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
				out.write(line.getBytes());
				line = in.readLine();
			}

			//close resource
			out.close();
			socket.close();

		} catch (UnknownHostException e) {
			//host not found, simply print on standard output
			e.printStackTrace();
		} catch (IOException e) {
			//I/O exception, simply print on standard output
			e.printStackTrace();
		}
	}

	/**
	 * main method to start java application
	 * @param args the program argument
	 */
	public static void main(String[] args) {
		//create GameClient object and calls its run method
		GameClient client = new GameClient();
		client.run();
	}

}
