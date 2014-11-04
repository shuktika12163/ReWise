package com.example.rewise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.os.Handler;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class Quiz {
	
	String _id;
	String code;
	String name;
	String category;
	Date starttime;
	Date endtime;
	ArrayList<Question> questions;
	boolean isInDB;
	boolean isTimed;
	static ArrayList<Quiz> al;
	
	static boolean received=false;
	static boolean once=false;
	
	public Quiz()
	{
		if(!once)
		{
			al=new ArrayList<Quiz>();
			once=true;
		}
		this.code="";
		this.name="";
		this.starttime=Calendar.getInstance().getTime();
		this.endtime=Calendar.getInstance().getTime();
		this.isTimed=false;
		this.questions=new ArrayList<Question>();
		this.isInDB=false;
		al.add(this);
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
	
	public void setCategory(String Category)
	{
		this.category=Category;
	}
	
	
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	public String getCategory()
	{
		return this.category;
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
			this.questions.add(q);
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
	
	public void deleteQuizDB()
	{
		
	}
	
	public static void deleteQuizDB(int _id)
	{
		
	}
	
		
	public void uploadToDB()
	{
		final ParseObject obj = new ParseObject("Quizzes");
		obj.put("Code", this.code);
		obj.put("Name", this.name);
		obj.put("StartTime", this.starttime);
		obj.put("EndTime", this.endtime);
		obj.put("isTimed", this.isTimed);
		obj.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				setObjectId(obj.getString("Code"), obj.getObjectId());
			}
		});
		this.isInDB=true;
	}
	
	static private void setObjectId(final String title, final String objId)
	{
		for(Quiz q: al)
		{
			if(title==q.code)
				q._id=objId;
		}
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Log.d("asd", title+objId);
			}
		}, 200);
	}
	
}
