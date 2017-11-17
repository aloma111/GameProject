package question;

import java.io.Serializable;

/**
 * multi-choice answer
 */
@SuppressWarnings("serial")
class MultiChoiceAnswer extends Answer implements Serializable{
    private String answer;

    public MultiChoiceAnswer(int questionID, String answerType, char result, String answer) {
        super(questionID, answerType, result);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }    
}
