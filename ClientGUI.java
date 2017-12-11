/**
 * 
 * Author: Moath Alomar
 * Student ID: 2283736
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The ClientGUI create Client, run it
 * then display question, get answer, send to, receive from server
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	 * background thread that control the connection with server
	 */
	private GameClient client;
			
	/**
	 * server host
	 */
	private String serverHost;
	
	/**
	 * server port
	 */
	private int serverPort;
	
	/**
	 * constructor
	 * 
	 * @param host server host
	 * @param port server port
	 */
	public ClientGUI(String host, int port) {
		this.serverHost = host;
		this.serverPort = port;
		initialize();
	}

	/**
	 * true/false answer
	 */
	private Boolean trueFalseAnswer;
	
	/**
	 * multi/single choice answers
	 */
	private List<Integer> multiChoiceAnswers = new ArrayList<>();

	/**
	 * scroll panel of text area of message
	 */
	private JScrollPane jScrollPane;
	
	/**
	 * create components
	 */
	private void initialize(){
		
		btnConnect = new JButton("Connect to Server");
		btnClose = new JButton("Close Client");
		btnAnswer = new JButton("Answer");
		
		txtMessage = new JTextArea();
		txtMessage.setEditable(false);
		
		jScrollPane = new JScrollPane();	
		jScrollPane.setPreferredSize(new Dimension(-1, 200));
		jScrollPane.setViewportView(txtMessage);
		
		JPanel addBtnPanel = new JPanel();
		addBtnPanel.add(btnConnect);
		addBtnPanel.add(btnAnswer);
		addBtnPanel.add(btnClose);
		
		clientQuestionPanel = new ClientQuestionPanel(this, null);
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
		
		btnAnswer.setEnabled(false);

		add(addBtnPanel, BorderLayout.SOUTH);
		add(jScrollPane, BorderLayout.NORTH);
		
		//set frame properties
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 800);
		setLocationRelativeTo(null); //center screen
	}	
	
	/**
	 * answer true/false question
	 * @param answer user answer
	 */
	public void setTrueFalseAnswer(boolean answer){
		this.trueFalseAnswer = answer;
	}
	
	/**
	 * answer multi choice question
	 * @param answer user answer
	 */
	public void setMultichoiceAnswer(int answer){
		if (!multiChoiceAnswers.contains(answer)){
			multiChoiceAnswers.add(answer);
			Collections.sort(multiChoiceAnswers);
		}
	}
	
	/**
	 * answer single choice question
	 * @param answer user answer
	 */
	public void setSinglechoiceAnswer(int answer){
		multiChoiceAnswers.clear();
		multiChoiceAnswers.add(answer);
	}
	
	/**
	 * send answer to server
	 */
	private void doAnswer(){		
		try {
			client.doAnswer();
			clearAnswer();//clear all answers
		} catch (IOException e) {
			//exception, simply print to standard output
			e.printStackTrace();
		}
	}
	
	/**
	 * clear answer
	 */
	public void clearAnswer(){
		trueFalseAnswer = null;
		multiChoiceAnswers.clear();
	}
	
	/**
	 * connect to server
	 * @param host host
	 * @param port port
	 */
	private void connect(){
		//create GameClient object and calls its run method
		client = new GameClient(this, serverHost, serverPort);
		client.start();
		
		//not allow to connect again
		btnConnect.setEnabled(false);
		btnAnswer.setEnabled(true);
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
		client.setRunning(false);//exit client background thread
		System.exit(0);
	}
	
	/**
	 * display message on text area
	 * @param msg message
	 */
	public void printMessage(String msg){
		txtMessage.append(msg + "\n");
		jScrollPane.getViewport().setViewPosition(new Point(0,txtMessage.getDocument().getLength()));
	}
	
	/**
	 * main method to start java application
	 * @param args the program argument
	 */
	public static void main(String[] args) {
		
		//validate argument
		if (args.length != 2){
			System.out.println("Usage: java ClientGUI <server host> <server port>");
			return;
		}
		
		int port = 0;
		try{
			port = Integer.parseInt(args[1]);
		}catch(Exception e){
			System.out.println("Port is invalid. Usage: java ClientGUI <server host> <server port>");
			return;
		}
		
		//create ClientGUI object
		ClientGUI client = new ClientGUI(args[0], port);
		client.setVisible(true);
	}

	/**
	 * get true/false answer
	 * @return true/false answer (user answers)
	 */
	public Boolean getTrueFalseAnswer() {
		return trueFalseAnswer;
	}

	/**
	 * get multi choice answer
	 * @return multi choice answer
	 */
	public List<Integer> getMultiChoiceAnswers() {
		return multiChoiceAnswers;
	}
	
}
