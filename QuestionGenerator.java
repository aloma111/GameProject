
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
	 * a unique instance object of QuestionGenerator
	 */
	private static QuestionGenerator questionGenerator;
	
	/**
	 * private constructor
	 * the client code cannot create this object
	 */
	private QuestionGenerator(){
		
	}
	
	/**
	 * get instance of QuestionGenerator
	 * @return a unique instance object of QuestionGenerator
	 */
	public static QuestionGenerator instance(){
		if (questionGenerator == null){
			questionGenerator = new QuestionGenerator();
		}
		return questionGenerator;
	}
}
