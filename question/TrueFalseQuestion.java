/**
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The TrueFalseQuestion represents a true/false question.
 * It is derived class of Question
 */

package question;

import java.io.Serializable;

/**
 * true false question
 */
@SuppressWarnings("serial")
public class TrueFalseQuestion extends Question  implements Serializable{
	
	/**
	 * correct answer
	 */
    private boolean correctAnswer;

    /**
     * constructor
     * 
     * @param questionID question ID
     * @param questionType question type
     * @param question question content
     * @param correctAnswer correct answer
     */
    public TrueFalseQuestion(int questionID, String questionType, String question, 
    		boolean correctAnswer) {
        super(questionID, questionType, question);
        this.correctAnswer = correctAnswer;
    }

    /**
     * accessor method of correct answer
     * @return correct answer
     */
    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * mutator method of correct answer
     * @param correctAnswer correct answer
     */
    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
}
