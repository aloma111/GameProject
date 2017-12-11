/**
 * 
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The TrueFalseAnswer represents a true/false answer.
 * It is derived class of Answer
 */

package question;

import java.io.Serializable;

/**
 * true false answer
 */
@SuppressWarnings("serial")
public class TrueFalseAnswer extends Answer implements Serializable{
	
	/**
	 * user answer
	 */
    private boolean answer;

    /**
     * constructor method
     * 
     * @param questionID question id
     * @param answer user answer
     */
    public TrueFalseAnswer(int questionID, boolean answer) {
        super(questionID);
        this.answer = answer;
    }

    /**
     * accessor method of answer
     * @return user answer
     */
    public boolean isAnswer() {
        return answer;
    }

    /**
     * mutator method of answer
     * @param answer user answer
     */
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }    
}
