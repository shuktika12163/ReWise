package com.example.tobedeleted;

public class MockStatModel {
	//User
	String UID;
	
	//Quiz
	private String QuizName;
	private String ZID;
	
	//Course
	String CourseName;
	private String CID;
	
	//Question
	String QID;
	
	//Designation
	Boolean Designation;
	
	//Arrays
	int response[];
	int correct[];
	
	//Background Image
	private int icon;

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}

	public String getZID() {
		return ZID;
	}

	public void setZID(String zID) {
		ZID = zID;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getQuizName() {
		return QuizName;
	}

	public void setQuizName(String quizName) {
		QuizName = quizName;
	}
}
