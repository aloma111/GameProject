package question;

import java.io.Serializable;

/**
 * true false question
 */
@SuppressWarnings("serial")
public class TrueFalseQuestion extends Question  implements Serializable{
	
    private boolean correctAnswer;

    public TrueFalseQuestion(int questionID, String questionType, String question, 
    		boolean correctAnswer) {
        super(questionID, questionType, question);
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
}
