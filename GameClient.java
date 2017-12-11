
/**
 * 
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: Game client open a secure connection to the TCP secure socket server
 * read the command and send to server
 * Any response from server will be printed on standard output.
 * This response is handled by a separated thread
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;

import question.MultiChoiceAnswer;
import question.MultiChoiceQuestion;
import question.Question;
import question.TrueFalseAnswer;
import question.TrueFalseQuestion;

public class GameClient extends Thread{

	/**
	 * server host
	 */
	private String serverHost;
	
	/**
	 * server port
	 */
	private int serverPort;
	
	/**
	 * reference to GUI to display message
	 */
	private ClientGUI clientGUI;
	
	/**
	 * flag that is true if this thread is running
	 */
	private volatile boolean isRunning = true;
	
	/**
	 * current question received from server
	 */
	private Question question = null;
	
	/**
	 * output
	 */
	private ObjectOutputStream outputStream;
	
	/**
	 * input
	 */
	private ObjectInputStream inputStream;
	
	/**
	 * constructor
	 * 
	 * @param clientGUI reference to GUI
	 */
	public GameClient(ClientGUI clientGUI, String serverHost, int serverPort) {
		this.clientGUI = clientGUI;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
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
			Socket socket = sf.createSocket(serverHost, serverPort);			

			//input, output
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
			
			//loop to read message from server, process and send message back to server
			while (isRunning){
				
				//read message from server
				Message msg = (Message)inputStream.readObject();
				
				//process message from server
				processMessage(msg);
			}
						
			//close resource
			inputStream.close();
			outputStream.close();
			socket.close();

		} catch (UnknownHostException e) {
			clientGUI.printMessage(e.getMessage());
		} catch (IOException e) {
			clientGUI.printMessage(e.getMessage());
		} catch (ClassNotFoundException e) {
			clientGUI.printMessage(e.getMessage());
		}
	}
	
	/**
	 * process message from server
	 * @param msg message
	 */
	private void processMessage(Message msg){
		if (msg.getAnswer() != null){//other clients sent answer
			clientGUI.printMessage(msg.getContent());
			msg = new Message();
			msg.setQuestion(null);
			msg.setAnswer(null);
			try {
				outputStream.writeObject(msg);
			} catch (IOException e) {
				// exception, simply print to console
				e.printStackTrace();
			}	
		}else if (msg.getContent() != null && !msg.getContent().equals("")){
			clientGUI.printMessage(msg.getContent());
		}else{
			question = msg.getQuestion();
			clientGUI.updateQuestionPanel(new ClientQuestionPanel(clientGUI, question));
			
		}
	}

	/**
	 * setter of isRunning
	 * @param isRunning thread is running
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 * player click answer button will call this method
	 * @throws IOException I/O Exception
	 */
	public void doAnswer() throws IOException {
		if (question == null){//no question
			clientGUI.printMessage("There is no question");
		}else {
			
			if (question instanceof TrueFalseQuestion){
				if (clientGUI.getTrueFalseAnswer() == null){
					clientGUI.printMessage("Please choose true or false");
				}else{
					TrueFalseQuestion q = (TrueFalseQuestion)question;
					TrueFalseAnswer a = new TrueFalseAnswer(q.getQuestionID(), clientGUI.getTrueFalseAnswer());
					
					//create message and send to server
					Message msg = new Message();
					msg.setQuestion(q);
					msg.setAnswer(a);
					
					outputStream.writeObject(msg);					
				}
			}else{//multi or single choice
				if (clientGUI.getMultiChoiceAnswers().size() == 0){
					clientGUI.printMessage("Please choose answer");
				}else{
					MultiChoiceQuestion q = (MultiChoiceQuestion)question;
					
					List<Integer> answers = clientGUI.getMultiChoiceAnswers();
					String sAnswers = "";
					for (Integer answer: answers){
						sAnswers += (char)('A' + answer);
					}
					
					MultiChoiceAnswer a = new MultiChoiceAnswer(q.getQuestionID(), sAnswers);
					
					//create message and send to server
					Message msg = new Message();
					msg.setQuestion(q);
					msg.setAnswer(a);
					
					outputStream.writeObject(msg);					
				}
			}
		}
	}
}
