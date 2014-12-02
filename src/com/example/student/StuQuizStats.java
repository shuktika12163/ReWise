package com.example.student;

import java.util.ArrayList;

import android.R;

import com.example.rewise.Course;
import com.example.rewise.globalVariables;
import com.example.tobedeleted.Constants;
import com.example.tobedeleted.MapQuizToCourse;
import com.example.tobedeleted.MockStatModel;

public class StuQuizStats {
	float attendence;
	float classaverage;
	float highest;
	String name;
	String code;
	int icon;

	
	public StuQuizStats(String zid,String cid){
		//Connect to Parse and Receive Data from Statistics Table
		//Initiate Statistics Calculation
		this.code=zid;
		
		ArrayList<MockStatModel> Statistics=MockStatModel.downloadAllStats();//Constants.Statistics
		for(MockStatModel i:Statistics){
			System.out.println(i.getCID());
			System.out.println(i.getZID());
			if(i.getCID()==cid && i.getZID()==zid){
				icon=globalVariables.icon; //i.getIcon();
				name=i.getQuizName();
			}
		}
		CalculateStats();
	}
	
	public void CalculateStats(){
		attendence=CalculateClassAttendence();
		classaverage=CalculateClassAverage();
		highest=CalculateClassHighest();
		
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


}
