package com.example.tobedeleted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MockStatModel {
	//User
	private String UID;
	
	//Quiz
	private String QuizName;
	private String ZID;
	
	//Course
	private String CourseName;
	private String CID;
	
	//Question
	private String QID;
	
	//Designation
	private Boolean Designation;
	
	//Arrays
	private int response[];
	private int correct[];
	
	//Correct
	private boolean isCorrect;
	
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

	public String getQuizName() {
		return QuizName;
	}

	public void setQuizName(String quizName) {
		QuizName = quizName;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public String getQID() {
		return QID;
	}

	public void setQID(String qID) {
		QID = qID;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public int[] getCorrect() {
		return correct;
	}

	public void setCorrect(int correct[]) {
		this.correct = correct;
	}

	public int[] getResponse() {
		return response;
	}

	public void setResponse(int response[]) {
		this.response = response;
	}

	public Boolean getDesignation() {
		return Designation;
	}

	public void setDesignation(Boolean designation) {
		Designation = designation;
	}
	
	public static boolean uploadStats(ArrayList<MockStatModel> alStats)
	{
		List<ParseObject> lPOs=new ArrayList<ParseObject>();
		for(MockStatModel each : alStats)
		{
			ParseObject po=convertStatToParse(each);
			lPOs.add(po);
		}
		try {
			ParseObject.saveAll(lPOs);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.e("asd",e.getMessage(),e);
			return false;
		}
		return true;
	}

	private static ParseObject convertStatToParse(MockStatModel stat) {
		ParseObject po=new ParseObject("Statistics");
		ArrayList<Integer> al=new ArrayList<Integer>();
		for(int i=0;i<stat.correct.length;i++)
			al.add(stat.correct[i]);
		po.put("Correct",al);
		po.put("CourseID", stat.CID);
		po.put("CourseName", stat.CourseName);
		po.put("Designation", stat.Designation);
		po.put("QuestionID", stat.QID);
		po.put("QuizID", stat.ZID);
		po.put("QuizName", stat.QuizName);
		al=new ArrayList<Integer>();
		for(int i=0;i<stat.response.length;i++)
			al.add(stat.response[i]);
		po.put("Response", al);
		po.put("UserID", stat.UID);
		po.put("isCorrect",stat.isCorrect);
		return po;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public static ArrayList<MockStatModel> downloadAllStats()
	{
		ArrayList<MockStatModel> alStats=new ArrayList<MockStatModel>();
		ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Statistics");
		try {
			List<ParseObject> lPo=query.find();
			for(ParseObject each : lPo)
			{
				MockStatModel stat=parseStat(each);
				alStats.add(stat);
			}
		} catch (ParseException e) {
			Log.e("asd",e.getMessage(),e);
			//return null;
		}
		return alStats;
	}

	private static MockStatModel parseStat(ParseObject po) {
		MockStatModel stat=new MockStatModel();
		stat.setCorrect(convertListToArray(po.getList("Correct")));
		stat.setCID(po.getString("CourseID"));
		stat.setCourseName(po.getString("CourseName"));
		stat.setDesignation(po.getBoolean("Designation"));
		stat.setQID(po.getString("QuestionID"));
		stat.setZID(po.getString("QuizID"));
		stat.setQuizName(po.getString("QuizName"));
		stat.setResponse(convertListToArray(po.getList("Response")));
		stat.setUID(po.getString("UserID"));
		stat.setCorrect(po.getBoolean("isCorrect"));
		return stat;
	}
	
	private static int[] convertListToArray(List<Object> list)
	{
		int[] x=new int[list.size()];
		for(int i=0;i<list.size();i++)
			x[i]=(Integer) list.get(i);
		return x;
	}
	
}
