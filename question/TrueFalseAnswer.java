package question;

import java.io.Serializable;

/**
 * true false answer
 */
@SuppressWarnings("serial")
public class TrueFalseAnswer extends Answer implements Serializable{
	
    private boolean answer;

    public TrueFalseAnswer(int questionID, String answerType, char result, boolean answer) {
        super(questionID, answerType, result);
        this.answer = answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }    
}
