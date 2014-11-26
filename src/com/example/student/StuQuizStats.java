package com.example.student;

public class StuQuizStats {
	float attendence;
	float classaverage;
	float highest;
	String name;
	String code;
	int icon;
	
	public StuQuizStats(String uid,String zid,String cid){
		//Connect to Parse and Receive Data from Statistics Table
		//Initiate Statistics Calculation
		CalculateStats();
	}
	public void CalculateStats(){
		
	}
}