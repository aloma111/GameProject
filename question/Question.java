package question;

import java.io.Serializable;

/**
 *
 */
@SuppressWarnings("serial")
public class Question implements Serializable{
    protected int questionID;
    protected String questionType;
    protected String question;
    
    public Question(int questionID, String questionType, String question) {
        this.questionID = questionID;
        this.questionType = questionType;
        this.question = question;
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    
}
