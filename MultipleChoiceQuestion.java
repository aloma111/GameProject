package question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * multi-choice question
 */
@SuppressWarnings("serial")
public class MultiChoiceQuestion extends Question  implements Serializable{
    
    private String correctAnswer;
    private List<String> options = new ArrayList<>();
    private boolean multiAnswer;
    
    public MultiChoiceQuestion(int questionID, String questionType, 
    		String question, boolean status, String correctAnswer, boolean multiAnswer) {
        super(questionID, questionType, question);
        this.correctAnswer = correctAnswer;
        this.multiAnswer = multiAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public boolean isMultiAnswer() {
        return multiAnswer;
    }

    public void setMultiAnswer(boolean multiAnswer) {
        this.multiAnswer = multiAnswer;
    }
}
