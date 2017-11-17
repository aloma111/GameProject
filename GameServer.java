import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * Game Server
 */
public class GameServer {

	//server port
	private static int SERVER_PORT = 1234;
	
	//list of GameClientHandler object
	private List<GameClientHandler> clientHandlers;
	
	//constructor
	public GameServer(){
		clientHandlers = new ArrayList<>();
	}
	
	//run the sever
	public void run() throws NoSuchAlgorithmException, 
	KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException{
		try{
			
			SSLContext context;
		    KeyManagerFactory kmf;
		    KeyStore ks;

		    context = SSLContext.getInstance("TLS");
		    kmf = KeyManagerFactory.getInstance("SunX509");
		System.out.println("Test");
		    FileInputStream fin = new FileInputStream(Configuration.STORE_NAME);
		    ks = KeyStore.getInstance("JKS");
		    ks.load(fin, Configuration.STORE_PASS.toCharArray());

		    kmf.init(ks, Configuration.KEY_PASS.toCharArray());
		    context.init(kmf.getKeyManagers(), null, null);
		    SSLServerSocketFactory ssf = context.getServerSocketFactory();
		    
		    ServerSocket ss = ssf.createServerSocket(SERVER_PORT);
		    
		    while (true) {
		      
		    	//accept connection
		    	Socket s = ss.accept();
		      
		    	//add GameClientHandler object, add to list and start it as thread
		    	GameClientHandler clientHandler = new GameClientHandler(s);
		    	clientHandlers.add(clientHandler);
		    	clientHandler.start();
		    }
		    
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	//main method
	public static void main(String[] args) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
		GameServer server = new GameServer();
		server.run();
	}

	
}
