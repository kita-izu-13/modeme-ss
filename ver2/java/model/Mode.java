package model;

public class Mode {

	int id;
	String question;
	String answer;
	String comment;
	
	public Mode() {}
	
	public Mode(int id, String question,String answer,String comment) {
		this.id=id;
		this.question=question;
		this.answer=answer;
		this.comment=comment;
	}
	
	public Mode(int id) {
		this.id=id;
	}
	
	public Mode(String question,String answer,String comment) {
		this.question=question;
		this.answer=answer;
		this.comment=comment;
	}
	
	
	public int getId() { return id; }
	public String getQuestion() { return question; }
	public String getAnswer() { return answer; }
	public String getComment() { return comment; }
}
