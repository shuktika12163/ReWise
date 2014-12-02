package com.example.student;

import java.util.ArrayList;

import com.example.rewise.Course;
import com.example.tobedeleted.Constants;
import com.example.tobedeleted.MapQuizToCourse;

public class StuCourseStats{
	float attendence;
	float classaverage;
	float highest;
	String name;
	String code;
	int icon;
	ArrayList<StuQuizStats> AllQuizzes=new ArrayList();
	
	public StuCourseStats(String cid){
		//Connect to Parse and Receive Data from Statistics Table
		//Initiate Statistics Calculation
		this.code=cid;
		for(Course i:Constants.CourseObjects){
			if(i.getCode()==code){
				icon=i.getBack();
				name=i.getName();
			}
		}
		CalculateStats();
	}
	
	public void CalculateStats(){
		attendence=CalculateClassAttendence();
		classaverage=CalculateClassAverage();
		highest=CalculateClassHighest();
		for( MapQuizToCourse i : Constants.CtoQ){
			if(i.getCID()==code){
				System.out.println("Stats adding the Quiz ;)");
				StuQuizStats temp=new StuQuizStats(i.getZID(),code);
				System.out.println("Stats added the quiz");
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
	
	public ArrayList<StuQuizStats> getQuizStats(){
		return AllQuizzes;
	}
	
}