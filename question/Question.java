/**
 * 
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The Question represents a question
 * It is super class for TrueFalseQuestion, MultiChoiceQuestion...
 */

package question;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Question implements Serializable{
	
	/**
	 * question ID
	 */
    protected int questionID;
    
    /**
     * question  type
     */
    protected String questionType;
    
    /**
     * question content
     */
    protected String question;
    
    /**
     * constructor method
     * 
     * @param questionID question ID
     * @param questionType question type
     * @param question question content
     */
    public Question(int questionID, String questionType, String question) {
        this.questionID = questionID;
        this.questionType = questionType;
        this.question = question;
    }

    /**
     * accessor method of questionID
     * @return question ID
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * accessor method of questionType
     * @return question type
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * accessor method of question
     * @return question content
     */
    public String getQuestion() {
        return question;
    }

    /**
     * mutator method of question
     * @param question question content
     */
    public void setQuestion(String question) {
        this.question = question;
    }
}
