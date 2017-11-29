/**
 * 
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: The MultiChoiceQuestion represents a multi choice question.
 * It is derived class of Question
 */
package question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MultiChoiceQuestion extends Question  implements Serializable{
    
	/**
	 * correct answer
	 */
    private String correctAnswer;
    
    /**
     * list of options
     */
    private List<String> options = new ArrayList<>();
    
    /**
     * is multi answer
     */
    private boolean multiAnswer;
    
    /**
     * constructor
     * 
     * @param questionID question ID
     * @param questionType question type
     * @param question question content
     * @param status status
     * @param correctAnswer correct answer
     * @param multiAnswer mutl answer
     */
    public MultiChoiceQuestion(int questionID, String questionType, 
    		String question, boolean status, String correctAnswer, boolean multiAnswer) {
        super(questionID, questionType, question);
        this.correctAnswer = correctAnswer;
        this.multiAnswer = multiAnswer;
    }

    /**
     * accessor method of correctAnswer
     * @return correctAnswer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * mutator method of correctAnswer
     * @param correctAnswer correct answer
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * accessor method of options
     * @return options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * mutator method of options
     * @param options list of options
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * accessor method of multiAnswer
     * @return multiAnswer
     */
    public boolean isMultiAnswer() {
        return multiAnswer;
    }

    /**
     * mutator method of multiAnswer
     * @param multiAnswer is multi answer
     */
    public void setMultiAnswer(boolean multiAnswer) {
        this.multiAnswer = multiAnswer;
    }
}
