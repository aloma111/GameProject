import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import question.MultiChoiceQuestion;
import question.Question;
import question.TrueFalseQuestion;

/**
 * 
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: Singleton pattern to get the questions
 * This class loads questions from file into list
 * Client code can get the random question from this class
 * 
 */
public class QuestionGenerator {
	
	/**
	 * current question index
	 */
	private int index = -1;
	
	/**
	 * questions list
	 */
	private List<Question> questions;
	
	/**
	 * questions file name
	 */
	private static final String QUESTIONS_FILENAME = "questions.txt";
	
	/**
	 * a unique instance object of QuestionGenerator
	 */
	private static QuestionGenerator questionGenerator;
	
	/**
	 * private constructor
	 * the client code cannot create this object
	 * @throws FileNotFoundException file not found
	 */
	private QuestionGenerator() throws FileNotFoundException{
		loadQuestions();
		
		//random questions
		for (int i = 0; i < questions.size(); i++){
			int randomIndex = (int)(Math.random() * questions.size());
			//swap
			Question temp = questions.get(i);
			questions.set(i, questions.get(randomIndex));
			questions.set(randomIndex, temp);					
		}
	}
	
	/**
	 * load questions
	 * @throws FileNotFoundException 
	 */
	private void loadQuestions() throws FileNotFoundException{
		
		questions = new ArrayList<>();
		
		//create Scanner object
		Scanner fileScanner = new Scanner(new File(QUESTIONS_FILENAME));
		
		//question index
		int questionIndex = 1;
		
		//read line by line
		while (fileScanner.hasNextLine()){
			String line = fileScanner.nextLine();
			if (line.startsWith("#")){//comment line
				continue;
			}
			
			Question question  = null;
			
			if (line.equals("1")){//true/false question
				question = new TrueFalseQuestion(questionIndex++, fileScanner.nextLine(), fileScanner.nextLine().equals("true"));
			}else{//multi choice question
				String content = fileScanner.nextLine();
				int numOptions = Integer.parseInt(fileScanner.nextLine());
				List<String> options = new ArrayList<>();
				for (int i = 0; i < numOptions; i++){
					options.add(fileScanner.nextLine());
				}
				String answer = fileScanner.nextLine();
				question = new MultiChoiceQuestion(questionIndex++, content, answer, answer.length() > 1);
				((MultiChoiceQuestion)question).setOptions(options);				
			}
			
			questions.add(question);
		}
		//close file
		fileScanner.close();
	}
	
	/**
	 * get instance of QuestionGenerator
	 * @return a unique instance object of QuestionGenerator
	 */
	public static QuestionGenerator instance(){
		if (questionGenerator == null){
			try {
				questionGenerator = new QuestionGenerator();
			} catch (FileNotFoundException e) {
				// exception, print to standard output
				e.printStackTrace();
			}
		}
		return questionGenerator;
	}
	
	/**
	 * get current question
	 * @return current question
	 */
	public Question getQuestion(){
		index = (index + 1) % questions.size();
		return questions.get(index);
	}
	
	/**
	 * get question by index
	 * @return question with index
	 */
	public Question getQuestion(int questionIndex){
		for (Question question: questions){
			if (question.getQuestionID() == questionIndex){
				return question;
			}
		}
		return null;
	}
}
