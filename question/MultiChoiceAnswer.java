/**
 * 
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The MultiChoiceAnswer represents a multi choice answer.
 * It is derived class of Answer
 */

package question;

import java.io.Serializable;

/**
 * multi-choice answer
 */
@SuppressWarnings("serial")
public class MultiChoiceAnswer extends Answer implements Serializable{
	
	/**
	 * user answer
	 */
    private String answer;

    /**
     * constructor method
     * 
     * @param questionID question ID
     * @param answer user answer
     */
    public MultiChoiceAnswer(int questionID, String answer) {
        super(questionID);
        this.answer = answer;
    }

    /**
     * accessor method of answer
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * mutator method of answer
     * @param answer user answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }    
}
