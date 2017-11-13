/**
 * Singleton pattern to get the questions
 */
public class QuestionGenerator {
	
	private static QuestionGenerator questionGenerator;
	
	//private constructor
	private QuestionGenerator(){
		
	}
	
	//get instance
	public static QuestionGenerator instance(){
		if (questionGenerator == null){
			questionGenerator = new QuestionGenerator();
		}
		return questionGenerator;
	}
}
