package com.example.rewise;

import java.util.ArrayList;

import com.example.instuctor.InstrCourseStats;
import com.example.instuctor.InstrQuizStats;
import com.example.student.StuCourseStats;
import com.example.student.StuQuizStats;

public class globalVariables {
	public static String UID;
	public static String QID;
	public static int icon=R.drawable.eco;
	public static ArrayList<InstrCourseStats> CStatistics=new ArrayList();
	public static ArrayList<StuCourseStats> CStuStatistics=new ArrayList();
	public static ArrayList<InstrQuizStats> QStatistics=new ArrayList();
	public static ArrayList<StuQuizStats> QStuStatistics=new ArrayList();
	public static Quiz quiz;
	public static boolean designation;
}
