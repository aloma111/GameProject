import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

/**
 * Game client
 */
public class GameClient {

	//server host
	private static String SERVER_HOST = "localhost";
	
	//server port
	private static int SERVER_PORT = 1234;		
		
	public void run(){
		
		 System.setProperty("javax.net.ssl.trustStore", Configuration.STORE_NAME);
		 
		 SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		 try {
			Socket socket = sf.createSocket(SERVER_HOST, SERVER_PORT);
			
			//write some words
			OutputStream out = socket.getOutputStream();
			out.write("hello\n".getBytes());
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = in.readLine();
			while (line != null){
				System.out.println(line);
				line = in.readLine();
			}
			
		    out.close();
		    socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//main method
	public static void main(String[] args) {
		GameClient client = new GameClient();
		client.run();
	}

}
