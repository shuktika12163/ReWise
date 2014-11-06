package com.example.rewise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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
	String courseCode;
	Date starttime;
	Date endtime;
	ArrayList<Question> questions;
	boolean isInDB;
	boolean isTimed;
	static ArrayList<Quiz> al;
	
	//static boolean received=false;
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
		this.category="";
		this.courseCode="";
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
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
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
	
	public static List<Quiz> downloadAllQuizzesFromDB()
	{
		List<Quiz> lQuizzes=new ArrayList<Quiz>();
		ParseQuery<ParseObject> queryQuizzes = new ParseQuery<ParseObject>("Quizzes");
        queryQuizzes.orderByDescending("createdAt");
        try {
            List<ParseObject> lParseQuizzes = queryQuizzes.find();
            for (ParseObject poQuiz : lParseQuizzes)
            {
            	Quiz quiz = new Quiz();
                quiz.setCode(poQuiz.getString("Code"));
                quiz.setName(poQuiz.getString("Name"));
                quiz.setCategory(poQuiz.getString("Category"));
                quiz.setCourseCode(poQuiz.getString("CourseCode"));
                quiz.setTimed(poQuiz.getBoolean("isTimed"));
                quiz.setStarttime(poQuiz.getDate("StartTime"));
                quiz.setEndtime(poQuiz.getDate("EndTime"));
                quiz._id=poQuiz.getObjectId();
                quiz.isInDB=true;
                lQuizzes.add(quiz);
            }
            return lQuizzes;
        } catch (ParseException e) {
            Log.e("Error", e.getMessage(),e);
            e.printStackTrace();
        }
		return null;
	}
		
	public boolean uploadToDB()
	{
//		if(this.isInDB)
//		return true;

		final ParseObject obj = new ParseObject("Quizzes");
		obj.put("Code", this.code);
		obj.put("Name", this.name);
		obj.put("Category", this.category);
		obj.put("StartTime", this.starttime);
		obj.put("EndTime", this.endtime);
		obj.put("isTimed", this.isTimed);
		try{
			Log.d("asd","started saving");
			obj.save();
			Log.d("asd","done saving");
		}
		catch(Exception e){
			Log.d("asd","err saving");
			return false;
		}
		this.isInDB=true;
		this._id=obj.getObjectId();
		return true;
	}
	
	public boolean uploadQsToDB()
	{
		ArrayList<ParseObject> alPo=new ArrayList<ParseObject>();
		for(Question qq:this.questions)
		{
			ParseObject obj = new ParseObject("MapQuizQuestions");
			obj.put("QuizID", this._id);
			obj.put("QuestionID", qq._id);
			alPo.add(obj);
			Log.d("asd","u");
		}
		try {
			Log.d("asd","started saving all");
			ParseObject.saveAll(alPo);
		} catch (ParseException e) {
			Log.d("asd","err saving all");
			return false;
		}
		Log.d("asd","done saving all");
		return true;
	}
	
	public boolean downloadQsFromDB()
	{
		ArrayList<String> alQIDs=new ArrayList<String>();
		ParseQuery<ParseObject> queryQuestionId = new ParseQuery<ParseObject>("MapQuizQuestions");
		queryQuestionId.whereEqualTo("QuizID", this._id);
		queryQuestionId.addAscendingOrder("createdAt");
		try {
			ArrayList<ParseObject> alMapQuizQs=(ArrayList<ParseObject>) queryQuestionId.find();
			for(ParseObject parseObject:alMapQuizQs)
				alQIDs.add(parseObject.getString("QuestionID"));
			this.setQuestions((ArrayList<Question>) Question.downloadQsFromDB(alQIDs));
				
		} catch (ParseException e) {
			Log.d("asd","err downloading Quiz Qs");
			return false;
		}
		
		return true;
	}
	
}
