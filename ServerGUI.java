/**
 * 
 * Author: Mohammed Alsoheem
 * Student ID: 2283736
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The ServerGUI create GameServer, run it
 * then display message on text area
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ServerGUI extends JFrame{

	/**
	 * start server
	 */
	private JButton btnStart;
	
	/**
	 * close server
	 */
	private JButton btnClose;
	
	/**
	 * text area that stores
	 * messages
	 */
	private JTextArea txtMessage;
	
	/**
	 * server port
	 */
	private int serverPort;
	
	/**
	 * constructor
	 */
	public ServerGUI(int port){
		this.serverPort = port;
		initialize();
	}
	
	/**
	 * create components
	 */
	private void initialize(){
		
		btnStart = new JButton("Start Server");
		btnClose = new JButton("Close server");
		txtMessage = new JTextArea();
		txtMessage.setEditable(false);
		
		JScrollPane jScrollPane = new JScrollPane();		
		jScrollPane.setViewportView(txtMessage);
		
		JPanel addBtnPanel = new JPanel();
		addBtnPanel.add(btnStart);
		addBtnPanel.add(btnClose);
		
		
		//set event 
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//start server
				start();
			}
		});
		
		//set event 
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//close server
				close();
			}
		});

		add(addBtnPanel, BorderLayout.SOUTH);
		add(jScrollPane, BorderLayout.CENTER);
		
		//set frame properties
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 800);
		setLocationRelativeTo(null); //center screen
	}	
	
	/**
	 * start the server
	 */
	private void start(){
		GameServer server = new GameServer(this, serverPort);
		try {
			server.start();
			printMessage("Server is running");
		} catch (Exception e) {
			printMessage(e.getMessage());
		}
	}
	
	/**
	 * closer the server
	 */
	private void close(){
		System.exit(0);
	}
	
	/**
	 * display message on text area
	 * @param msg message
	 */
	public void printMessage(String msg){
		txtMessage.append(msg + "\n");
	}
	
	/**
	 * main method to start java application
	 * @param args the program argument
	 * @throws Exception if error
	 */
	public static void main(String[] args) throws Exception {
		
		//validate argument
		if (args.length != 1){
			System.out.println("Usage: java ServerGUI <server port>");
			return;
		}
		
		int port = 0;
		try{
			port = Integer.parseInt(args[0]);
		}catch(Exception e){
			System.out.println("Port is invalid. Usage: java ServerGUI <server port>");
			return;
		}
		
		//create ServerGUI object
		ServerGUI server = new ServerGUI(port);
		server.setVisible(true);
	}
	
	
}
