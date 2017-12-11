/**
 * 
 * Author: Parker Janke
 * Student ID: 2251871
 * Course number: CPSC 353
 * Section, assignment or exercise number: Class Project
 * Description: Message represents a message that is sent/received between
 * client and server
 */

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
	 * information for client
	 * 1. number of players
	 * 2. current player's score
	 * 3. highest score
	 * 4. lowest score
	 * 5. number of correct answers
	 * 6. number of incorrect answers
	 * 
	 */
	private String content;

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

	/**
	 * accessor method of content
	 * @return content content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * mutator method of content
	 * @param content content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
