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
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;

import question.Answer;
import question.MultiChoiceAnswer;
import question.MultiChoiceQuestion;
import question.Question;
import question.TrueFalseAnswer;
import question.TrueFalseQuestion;
/**
 * Game Server
 */
public class GameServer extends Thread{

	/**
	 * server port
	 */
	private int serverPort;
	
	/**
	 * list of GameClientHandler object
	 */
	private List<GameClientHandler> clientHandlers;
	
	/**
	 * reference to GUI to display message
	 */
	private ServerGUI serverGUI;
	
	/**
	 * correct score
	 */
	private static final int CORRECT_SCORE = 10;
	
	/**
	 * incorrect score
	 */
	private static final int INCORRECT_SCORE = -5;
	
	/**
	 * question generator
	 */
	private QuestionGenerator questionGenerator;
	
	/**
	 * number of correct answers
	 */
	private int numCorrectAnswers = 0;
	
	/**
	 * number of incorrect answers
	 */
	private int numInCorrectAnswers = 0;
	
	/**
	 * constructor
	 * initialize the array list
	 */
	public GameServer(ServerGUI serverGUI, int serverPort){
		clientHandlers = new ArrayList<>();
		this.serverPort = serverPort;
		this.serverGUI = serverGUI;
		
		//create unique instance of question generator
		questionGenerator = QuestionGenerator.instance();
		
		//get the first random question
		question = questionGenerator.getQuestion();
	}
	
	/**
	 * current question
	 */
	private Question question = null;
	
	/**
	 * Synchronized object to use this object
	 */
	private Object synObject = new Object();
	
	/**
	 * get current question
	 * @return current question
	 */
	public Question getCurrentQuestion(){
		return question;
	}
	
	/**
	 * run the sever
	 * @throws Exception if there is error in key store
	 */
	public void run(){
		
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
		    ServerSocket ss = ssf.createServerSocket(serverPort);
		    
		    //loop to wait for client connection
		    //if there is connection, the server open new thread
		    //the thread will handles this connection
		    while (true) {
		      
		    	//accept connection
		    	Socket s = ss.accept();
		      
		    	//add GameClientHandler object, add to list and start it as thread
		    	GameClientHandler clientHandler = new GameClientHandler(this, s);
		    	clientHandlers.add(clientHandler);
		    	clientHandler.start();
		    }
		    
		}catch(Exception e){
			
			//any exception, simply print to standard output
			serverGUI.printMessage(e.getMessage());
		}
	}

	/**
	 * get reference to server GUI
	 * @return server GUI
	 */
	public ServerGUI getServerGUI() {
		return serverGUI;
	}	
	
	/* 
	 * create information that sends to client
	 * 1. number of players
	 * 2. current player's score
	 * 3. highest score
	 * 4. lowest score
	 * 5. number of correct answers
	 * 6. number of incorrect answers
	 */ 
	public Message createMessage(int score){
		Message msg = new Message(); //create Message object
		
		StringBuffer content = new StringBuffer();
		
		synchronized (synObject) {
			
			content.append("Number of players: ").append(clientHandlers.size()).append("\n");
			content.append("Your score: ").append(score).append("\n");
			content.append("Highest score: ").append(calculateHighestScore()).append("\n");
			content.append("Lowest score: ").append(calculateLowestScore()).append("\n");
			content.append("Number of correct answers: ").append(numCorrectAnswers).append("\n");
			content.append("Number of incorrect answers: ").append(numInCorrectAnswers).append("\n");
			
			synObject.notifyAll();
		}
		
		msg.setContent(content.toString());
		return msg;
	}
	
	/**
	 * calculate the highest score for all users
	 * @return the highest score for all users
	 */
	private int calculateHighestScore(){
		int score = clientHandlers.get(0).getScore();
		for (int i = 1; i < clientHandlers.size(); i++){
			if (score < clientHandlers.get(i).getScore()){
				score = clientHandlers.get(i).getScore();
			}
		}
		return score;
	}
	
	/**
	 * calculate the lowest score for all users
	 * @return the lowest score for all users
	 */
	private int calculateLowestScore(){
		int score = clientHandlers.get(0).getScore();
		for (int i = 1; i < clientHandlers.size(); i++){
			if (score > clientHandlers.get(i).getScore()){
				score = clientHandlers.get(i).getScore();
			}
		}
		return score;
	}
	
	/**
	 * process answer from player
	 * @param handler one player (GameClientHandler)
	 * @param answer user answer
	 */
	public void doAnswer(GameClientHandler handler, Answer answer){
		if (answer == null){ //the client thread returns empty answer to move to next questions
			return;
		}
		synchronized (synObject) {

			if (answer.getQuestionID() == question.getQuestionID()){//current question
				if (question instanceof TrueFalseQuestion && answer instanceof TrueFalseAnswer){
					
					TrueFalseQuestion q = (TrueFalseQuestion) question;
					TrueFalseAnswer a = (TrueFalseAnswer) answer;
					if (q.isCorrectAnswer() == a.isAnswer()){
						//correct answer
						handler.setScore(handler.getScore() + CORRECT_SCORE);
					}else{//incorrect answer
						handler.setScore(handler.getScore() + INCORRECT_SCORE);
					}
				}else if (question instanceof MultiChoiceQuestion && answer instanceof MultiChoiceAnswer){
					MultiChoiceQuestion q = (MultiChoiceQuestion) question;
					MultiChoiceAnswer a = (MultiChoiceAnswer) answer;
					if (q.getCorrectAnswer().equalsIgnoreCase(a.getAnswer())){
						//correct answer
						handler.setScore(handler.getScore() + CORRECT_SCORE);
					}else{//incorrect answer
						handler.setScore(handler.getScore() + INCORRECT_SCORE);
					}
				}//others, ignore
				
				//send message to notify that the server got answer
				for (GameClientHandler client: clientHandlers){
					if (client != handler){
						//send current information to client
						Message msg = createMessage(client.getScore());
						msg.setAnswer(answer);//send the answer from answered client
						client.sendMessge(msg);
					}
				}
			}
			
			question = questionGenerator.getQuestion();
			
			synObject.notifyAll();
		}
	}
}

