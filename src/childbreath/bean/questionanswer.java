package childbreath.bean;

import org.springframework.stereotype.Component;

@Component
public class questionanswer {

	private Long questionId;
	private String answer;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
