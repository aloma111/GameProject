/**
 * 
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The answer represents an answer of a question.
 * It is super class for TrueFalseAnswer, MultiChoiceAnswer...
 */

package question;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Answer implements Serializable{
	
	/**
	 * unique question ID
	 */
    private int questionID;
    
    /**
     * result is C for correct or I for incorrect
     */
    private char result;

    /**
     * constructor method
     * 
     * @param questionID question id
     * @param result result is C or I
     */
    public Answer(int questionID, char result) {
        this.questionID = questionID;
        this.result = result;
    }

    /**
     * accessor method of questionID
     * @return questionID
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * mutator method of questionID
     * @param questionID question ID
     */
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    /**
     * accessor method of result
     * @return result
     */
    public char getResult() {
        return result;
    }

    /**
     * mutator method of result
     * @param result result is correct or incorrect
     */
    public void setResult(char result) {
        this.result = result;
    }
}
