package com.example.rewise;

import java.util.ArrayList;
import java.util.Date;

public class Quiz {
	
	String _id;
	String code;
	String name;
	Date starttime;
	Date endtime;
	ArrayList<Question> questions;
	boolean isInDB;
	boolean isTimed;
	
	public Quiz()
	{
		this.code="";
		this.name="";
		this.starttime=null;
		this.starttime=null;
		this.isTimed=false;
		this.questions.clear();
		this.isInDB=false;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStarttime() {
		return starttime;
	}
	
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	public Date getEndtime() {
		return endtime;
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	public void setDuration(int hrs, int min)
	{
		long start=this.starttime.getTime(); //in milliseconds
		long duration=hrs*60+min;
		duration*=60000;
		this.endtime.setTime(start+duration);
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public boolean addQuestion(Question q)
	{
		if(!this.questions.contains(q))
		{
			this.questions.remove(q);
			return true;
		}
		return false;
	}
	
	public boolean removeQuestion(Question q)
	{
		return this.questions.remove(q);
	}
	
	public boolean removeQuestion(int pos)
	{
		return this.questions.remove(pos) != null;
	}

	public boolean isTimed() {
		return isTimed;
	}

	public void setTimed(boolean isTimed) {
		this.isTimed = isTimed;
	}
	
	public void uploadToDB()
	{
		
	}
	
	public void deleteQuizDB()
	{
		
	}
	
	public static void deleteQuizDB(int _id)
	{
		
	}
	
	
	
}
