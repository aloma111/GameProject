import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import question.MultiChoiceQuestion;
import question.Question;
import question.TrueFalseQuestion;

@SuppressWarnings("serial")
public class ClientQuestionPanel extends JPanel{
	
	/**
	 * reference to question
	 */
	private Question question;
	
	/**
	 * question content
	 */
	private JTextArea textContent;
	
	/**
	 * constructor
	 * @param question question
	 */
	public ClientQuestionPanel(Question question){
		this.question = question;
		
		//initialize components
		initialize();
	}
	
	/**
	 * initialize components
	 */
	private void initialize(){
		
		if (question != null){
			
			//question label
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(new JLabel("Question: "));
			
			//question content
			textContent = new JTextArea();
			textContent.setEditable(false);
			textContent.setText(question.getQuestion());			
			add(textContent);
			
			//create option panel
			JPanel optionPanel = new JPanel();
			optionPanel.setPreferredSize(new Dimension(-1, 300));
			if (question instanceof TrueFalseQuestion){
				//True or False options
				JRadioButton rdoTrue = new JRadioButton("True");
				rdoTrue.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						doTrueFalseAnswer(true);						
					}
				});
				JRadioButton rdoFalse = new JRadioButton("False");
				rdoFalse.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						doTrueFalseAnswer(false);						
					}
				});
				ButtonGroup bg = new ButtonGroup();
				bg.add(rdoTrue);
				bg.add(rdoFalse);
				
				optionPanel.add(rdoTrue);
				optionPanel.add(rdoFalse);
			}else{//Multichoice question
				optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
				MultiChoiceQuestion multiChoiceQuestion = (MultiChoiceQuestion)question;
				if (multiChoiceQuestion.isMultiAnswer()){ //can choose one or many answers
					for (int i = 0; i < multiChoiceQuestion.getOptions().size(); i++){
						JCheckBox chkOption = new JCheckBox(multiChoiceQuestion.getOptions().get(i));
						final int index = i;
						chkOption.addActionListener(new ActionListener() {							
							@Override
							public void actionPerformed(ActionEvent e) {
								doMultichoiceAnswer(index);						
							}
						});
						optionPanel.add(chkOption);
					}
				}else{//single choice
					ButtonGroup bg = new ButtonGroup();
					for (int i = 0; i < multiChoiceQuestion.getOptions().size(); i++){
						
						JRadioButton rdoOption = new JRadioButton(multiChoiceQuestion.getOptions().get(i));
						final int index = i;
						rdoOption.addActionListener(new ActionListener() {							
							@Override
							public void actionPerformed(ActionEvent e) {
								doSinglechoiceAnswer(index);						
							}
						});
						bg.add(rdoOption);
						optionPanel.add(rdoOption);
					}
				}
			}
			
			add(optionPanel);
		}
	}
	
	private void doTrueFalseAnswer(boolean answer){
		System.out.println("doTrueFalseAnswer: " + answer);
	}
	
	private void doMultichoiceAnswer(int answer){
		System.out.println("doTruedoMultichoiceAnswerFalseAnswer: " + answer);
	}
	
	private void doSinglechoiceAnswer(int answer){
		System.out.println("doSinglechoiceAnswer: " + answer);
	}
}
