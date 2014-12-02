package com.example.instuctor;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.rewise.Course;
import com.example.tobedeleted.Constants;
import com.example.tobedeleted.MapQuizToCourse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class InstrCourseStats{
	float attendence;
	float classaverage;
	float highest;
	String name;
	String code;
	int icon;
	ArrayList<InstrQuizStats> AllQuizzes=new ArrayList();
	
	public InstrCourseStats(String cid){
		//Connect to Parse and Receive Data from Statistics Table
		//Initiate Statistics Calculation
		this.code=cid;
		Log.d("new","stats   cid   "+cid);
		for(Course i:InstrMainActivity.courseobjects){
			if(i.getCode().equals(code)){
				Log.d("new","stats   equals"+code);
				icon=i.getBack();
				name=i.getName();
			}
		}
		CalculateStats();
	}
	
	
	ArrayList<MapQuizToCourse> CtoQ;
	public void CalculateStats(){
		Log.d("new","stats   entered calcstats");
		attendence=CalculateClassAttendence();
		classaverage=CalculateClassAverage();
		highest=CalculateClassHighest();
		populateCtoQ();
		for( MapQuizToCourse i : CtoQ){
			if(i.getCID().compareTo(code)==0){
				Log.d("new","stats   cid==code     "+code);
				Log.d("new","stats   i zid     "+i.getZID());
				System.out.println("Stats adding the Quiz ;)");
				InstrQuizStats temp=new InstrQuizStats(i.getZID(),code);
				System.out.println("Stats added to the quiz");
				AllQuizzes.add(temp);
			}
		}
	}
	

	//need a percentage
	public float CalculateClassAverage(){
		return 0;
	}
	
	//need a percentage
	public float CalculateClassHighest(){
		return 0;
	}
	
	//need a percentage
	public float CalculateClassAttendence(){
		return 0;
	}
	
	//need a hashmap
	public float CalculateHashAttendence(){
		return 0;
	}
	
	//need a hashmap
	public float CalculateHashHighest(){
		return 0;
	}
	
	//need a hashmap
	public float CalculateHashAverage(){
		return 0;
	}
	
	public ArrayList<InstrQuizStats> getQuizStats(){
		return AllQuizzes;
	}
	
	public void setQuizStats(ArrayList<InstrQuizStats> a){
		AllQuizzes=a;
	}
	
	private void populateCtoQ() {
		ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("MapCourseQuiz");
		ArrayList<MapQuizToCourse> al=new ArrayList<MapQuizToCourse>();
		try {
			List<ParseObject> parseMapping=query.find();
			for(ParseObject each: parseMapping)
			{
				MapQuizToCourse mp=new MapQuizToCourse();
				mp.setCID(each.getString("CourseID"));
				mp.setZID(each.getString("ZID"));
				al.add(mp);
				Log.d("new","stats   CtoQ ## "+mp.getCID()+"  C    Z  "+mp.getZID());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		CtoQ=al;
	}
	
}