package com.example.rewise;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.internal.lq;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class Course {
	String _id;
	String code;
	String name;
	private boolean selected;
	int backimg;
	private ArrayList<Quiz> children;
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getBack() {
		return backimg;
	}
	
	public void setBack(int code) {
		this.backimg = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Quiz> getChildren()
    {
        return children;
    }
     
    public void setChildren(ArrayList<Quiz> children)
    {
        this.children = children;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public static List<Course> downloadAllCoursesFromDB()
	{
		List<Course> lCourses=new ArrayList<Course>();
		ParseQuery<ParseObject> queryCourses = new ParseQuery<ParseObject>("Courses");
        //queryCourses.orderByDescending("createdAt");
        try {
            List<ParseObject> lParseCourses = queryCourses.find();
            for (ParseObject poCourse : lParseCourses)
            {
//            	c.setChildren((ArrayList<Quiz>)QuizObjects.clone()); //without past
            	Course course = new Course();
            	course.setName(poCourse.getString("courseName"));
            	course.setCode(poCourse.getString("courseCode"));
            	course.setSelected(false);
            	course.setBack(getCourseIcon(course.getName()));
            	course._id=poCourse.getObjectId();
            	//TODO arrayList Quiz
            	course.setChildren(null);//course.setChildren(course.getQuizzes());
            	lCourses.add(course);
            }
            return lCourses;
        } catch (ParseException e) {
            Log.e("Error", e.getMessage(),e);
            e.printStackTrace();
        }
		return null;
	}
    
    public static int getCourseIcon(String courseName)
    {
    	if(courseName.equals("Mobile Computing"))
    		return R.drawable.mc;
    	if(courseName.equals("Advanced Programming"))
    		return R.drawable.ap;
    	if(courseName.equals("System Management"))
    		return R.drawable.sm;
    	if(courseName.equals("Embedded Logic Design"))
    		return R.drawable.eld;
    	if(courseName.equals("Economics"))
    		return R.drawable.eco;
    	if(courseName.equals("Applied Cryptography"))
    		return R.drawable.ac;
    	return 0;
    }
    
    //function call to download its quizzes which is null before
    public void downloadQuizzes()
    {
    	//List<String> lQuizIds=new ArrayList<String>();
    	ArrayList<Quiz> lQuizzes=new ArrayList<Quiz>();
    	ParseQuery<ParseObject> queryQuizzes = new ParseQuery<ParseObject>("MapCourseQuiz");
    	queryQuizzes.whereEqualTo("CourseID", this.getCode());
        //queryQuizzes.orderByDescending("createdAt");
        try
        {
            List<ParseObject> lParseQuizIds = queryQuizzes.find();
            for (ParseObject poCourse : lParseQuizIds)
            {
            	Quiz quiz;
            	quiz=Quiz.downloadQuizFromDB(poCourse.getString("QuizID"));
            	lQuizzes.add(quiz);
            }
            this.setChildren(lQuizzes);
        }
        catch (ParseException e) {
            Log.e("Error", e.getMessage(),e);
            e.printStackTrace();
        }
    }
    
    public static int getCount()
    {
    	ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Courses");
    	try {
			return query.count();
		} catch (ParseException e) {
			Log.e("asd",e.getMessage(),e);
		}
    	return 0;
    }
    
    @Override
    public String toString() {
    	return this.name+ "   "+ this.code;
    }
	
}
