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
     * constructor method
     * 
     * @param questionID question id
     */
    public Answer(int questionID) {
        this.questionID = questionID;
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

}
