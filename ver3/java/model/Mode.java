package model;

public class Mode {

	int id;
	String question;
	String answer;
	String comment;
	int miss;
	
	public Mode() {}
	
	public Mode(int id, String question,String answer,String comment,int miss) {
		this.id=id;
		this.question=question;
		this.answer=answer;
		this.comment=comment;
		this.miss=miss;
	}
	
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
	public int getMiss() { return miss; }
}
