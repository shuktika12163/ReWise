package com.example.tobedeleted;

import java.util.ArrayList;
import java.util.Date;

import android.util.Log;

import com.example.rewise.Course;
import com.example.rewise.Quiz;
import com.example.rewise.R;
import com.example.rewise.R.drawable;

public final class Constants {
	public static String UID;
	public static ArrayList<Course> CourseObjects=new ArrayList();
	public static ArrayList<Quiz> QuizObjects=new ArrayList();
	public static ArrayList<MapCourseToUser> CtoU=new ArrayList();
	public static ArrayList<MapQuizToCourse> CtoQ=new ArrayList();
	public static ArrayList<MockStatModel> Statistics=new ArrayList();
	public static void set(){
		CourseObjects.clear();
		QuizObjects.clear();
		CtoQ.clear();
		CtoU.clear();
		Statistics.clear();
		//Hard Code Quiz
		
		//MG done
		Quiz q=new Quiz();
		q.setCode("Q1");
		q.setEndtime(new Date());
		q.setStarttime(new Date());
		q.setName("Introduction to Android");
		QuizObjects.add(q);
		
		//Hard Code Course
		//MG done
		Course c=new Course();
    	c.setName("Mobile Computing");
    	c.setCode("CSE535");
    	c.setSelected(false);
    	c.setBack(R.drawable.mc);
    	c.setChildren((ArrayList<Quiz>)QuizObjects.clone());
    	QuizObjects.clear();
    	CourseObjects.add(c);
    	c=new Course();
    	c.setName("Advanced Programming");
    	c.setCode("CSE200");
    	c.setSelected(false);
    	c.setBack(R.drawable.ap);
    	c.setChildren(QuizObjects);
    	CourseObjects.add(c);
    	c=new Course();
    	c.setName("System Management");
    	c.setCode("CSE121");
    	c.setSelected(false);
    	c.setBack(R.drawable.sm);
    	c.setChildren(QuizObjects);
    	CourseObjects.add(c);
    	c=new Course();
    	c.setName("Embedded Logic Design");
    	c.setCode("ECE222");
    	c.setSelected(false);
    	c.setBack(R.drawable.eld);
    	c.setChildren(QuizObjects);
    	CourseObjects.add(c);
    	c=new Course();
    	c.setName("Economics");
    	c.setCode("ECO302");
    	c.setSelected(false);
    	c.setBack(R.drawable.eco);
    	c.setChildren(QuizObjects);
    	CourseObjects.add(c);
    	c=new Course();
    	c.setName("Applied Cryptography");
    	c.setCode("CSE400");
    	c.setSelected(false);
    	c.setBack(R.drawable.ac);
    	c.setChildren(QuizObjects);
    	CourseObjects.add(c);
    	
    	//Hard Code Mapping CtoU
    	//MG done
    	MapCourseToUser m1=new MapCourseToUser();
    	m1.CID="CSE535";
    	m1.UID="sarthak166@gmail.com";
    	m1 = new MapCourseToUser();
    	m1.CID="CSE535";
    	m1.UID="manang@gmail.com";
    	m1 = new MapCourseToUser();
    	m1.CID="CSE535";
    	m1.UID="shreya@gmail.com";
    	m1 = new MapCourseToUser();
    	m1.CID="CSE535";
    	m1.UID="chaitanya@gmail.com";
    	CtoU.add(m1);
    	
    	//Hard Code Mapping CtoQ
    	//MG done
    	MapQuizToCourse q1=new MapQuizToCourse();
    	q1.setCID("CSE535");
    	q1.setZID("Q1");
    	CtoQ.add((MapQuizToCourse)q1);
    	Log.e("I", CtoQ.get(0).getCID());
    	
    	//Hard Code Statistics
    	//MG done
    	MockStatModel m=new MockStatModel();
    	m.setUID("sarthak166@gmail.com");
    	m.setQID("1");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,2,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	
    	m.setUID("sarthak166@gmail.com");
    	m.setQID("2");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,2,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("sarthak166@gmail.com");
    	m.setQID("3");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,2,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("sarthak166@gmail.com");
    	m.setQID("4");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("shreya@gmail.com");
    	m.setQID("1");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("shreya@gmail.com");
    	m.setQID("2");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("shreya@gmail.com");
    	m.setQID("3");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("shreya@gmail.com");
    	m.setQID("4");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,2,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("manang@gmail.com");
    	m.setQID("1");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("manang@gmail.com");;
    	m.setQID("2");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("manang@gmail.com");
    	m.setQID("3");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,2,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
    	
    	m.setUID("manang@gmail.com");
    	m.setQID("4");
    	m.setQuizName("Introduction to Android");
    	m.setCID("CSE535");
    	m.setCourseName("Mobile Computing");
    	m.setZID("Q1");
    	m.setCorrect(new int[]{1,2,3});
    	m.setResponse(new int[]{1,2,3});
    	m.setDesignation(false);
    	Statistics.add(m);
    	m=new MockStatModel();
	}
	
	
}
