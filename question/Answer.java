package question;

import java.io.Serializable;

/**
 * answer
 */
@SuppressWarnings("serial")
public class Answer implements  Serializable{
    private int questionID;
    private String answerType;
    private char result;

    public Answer(int questionID, String answerType, char result) {
        this.questionID = questionID;
        this.answerType = answerType;
        this.result = result;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public char getResult() {
        return result;
    }

    public void setResult(char result) {
        this.result = result;
    }
}
