package com.example.student;

public class StuCourseStats {
	float attendence;
	float classaverage;
	float highest;
	String name;
	String code;
	int icon;
	
	public StuCourseStats(String uid,String cid){
		//Connect to Parse and Receive Data from Statistics Table
		//Initiate Statistics Calculation
		CalculateStats();
	}
	public void CalculateStats(){
		
	}
}