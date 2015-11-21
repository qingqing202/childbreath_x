package childbreath.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class questionnaire {
	private long id;
	private String name;
	private List<Long> questions;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Long> questions) {
		this.questions = questions;
	}	
}
