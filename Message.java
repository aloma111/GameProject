import java.io.Serializable;

import question.Answer;
import question.Question;

@SuppressWarnings("serial")
public class Message implements Serializable{
	
	/**
	 * question
	 */
	private Question question;
	
	/**
	 * answer
	 */
	private Answer answer;

	/**
	 * accessor method of question
	 * return question question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * mutator method of question
	 * @param question question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * accessor method of answer
	 * @return answer answer
	 */
	public Answer getAnswer() {
		return answer;
	}

	/**
	 * mutator method of answer
	 * @param question answer
	 */
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	
}
