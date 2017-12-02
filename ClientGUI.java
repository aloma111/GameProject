/**
 * 
 * Author: Mohammed Alsoheem
 * Student ID: 2283736
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The ClientGUI create Client, run it
 * then display question, get answer, send to, receive from server
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame{

	/**
	 * connect to server
	 */
	private JButton btnConnect;
	
	/**
	 * answer
	 */
	private JButton btnAnswer;
	
	/**
	 * close client
	 */
	private JButton btnClose;
	
	/**
	 * text area that stores
	 * messages
	 */
	private JTextArea txtMessage;
	
	/**
	 * panel to display question
	 */
	private ClientQuestionPanel clientQuestionPanel;
	
	/**
	 * constructor
	 */
	public ClientGUI(){
		initialize();
	}
	

	/**
	 * create components
	 */
	private void initialize(){
		
		btnConnect = new JButton("Connect to Server");
		btnClose = new JButton("Close Client");
		btnAnswer = new JButton("Answer");
		
		txtMessage = new JTextArea();
		txtMessage.setEditable(false);
		
		JScrollPane jScrollPane = new JScrollPane();	
		jScrollPane.setPreferredSize(new Dimension(-1, 200));
		jScrollPane.setViewportView(txtMessage);
		
		JPanel addBtnPanel = new JPanel();
		addBtnPanel.add(btnConnect);
		addBtnPanel.add(btnAnswer);
		addBtnPanel.add(btnClose);
		
		clientQuestionPanel = new ClientQuestionPanel(null);
		add(clientQuestionPanel, BorderLayout.CENTER);
		
		//set event 
		btnConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//connect to server
				connect();
			}
		});
		
		//set event 
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//close client
				close();
			}
		});
		
		//set event 
		btnAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//answer
				doAnswer();
			}
		});

		add(addBtnPanel, BorderLayout.SOUTH);
		add(jScrollPane, BorderLayout.NORTH);
		
		
		
		//set frame properties
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 800);
		setLocationRelativeTo(null); //center screen
	}	
	
	/**
	 * send answer to server
	 */
	private void doAnswer(){
		
	}
	
	/**
	 * connect to server
	 */
	private void connect(){
		//create GameClient object and calls its run method
		GameClient client = new GameClient(this);
		client.start();
	}
	
	/**
	 * update question panel
	 */
	public void updateQuestionPanel(ClientQuestionPanel panel){
		remove(clientQuestionPanel);
		clientQuestionPanel = panel;
		add(panel, BorderLayout.CENTER);
		validate();
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
	 */
	public static void main(String[] args) {
		//create ClientGUI object
		ClientGUI client = new ClientGUI();
		client.setVisible(true);
	}
	
}
