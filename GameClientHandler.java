import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * GameClientHandler handles the player connection
 */
public class GameClientHandler extends Thread{
	
	/**
	 * reference to socket
	 */
	private Socket socket;
	
	//constructor
	public GameClientHandler(Socket socket) {
		this.socket = socket;
	}

	/**
	 * 
	 */
	public void run(){
		BufferedReader in;
		PrintStream out;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			
			out.println("Got message: " + in.readLine());
			
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
