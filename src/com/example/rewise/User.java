package com.example.rewise;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class User {

	String _id;
	String email;
	boolean designation;
	List<String> alCourses;
	ParseObject parseUser;
	
	public User(String email, boolean designation) {
		this._id="";
		this.email=email;
		this.designation=designation;
		this.alCourses=new ArrayList<String>();
		this.parseUser=null;
		this.createOrFetch();
	}

	//assuming we get the user
	private void createOrFetch()
	{
		ParseQuery<ParseObject> queryUser = new ParseQuery<ParseObject>("user");
		queryUser.whereEqualTo("email", this.email);
		try {
			List<ParseObject> lPo=queryUser.find();
			if(lPo.size()>0)
			{
			ParseObject po=lPo.get(0);
			this._id=po.getObjectId();
			this.designation=po.getBoolean("designation");
			this.alCourses=po.getList("courses");
			this.parseUser=po;
			return;
			}
		} catch (ParseException e) {
			Log.e("asd",e.getMessage(),e);
		}
		ParseObject newUser=new ParseObject("user");
		newUser.put("designation", this.designation);
		List<String> x=new ArrayList<String>();
		newUser.put("courses",x);
		newUser.put("email",this.email);
		try {
			newUser.save();
			this.parseUser=newUser;
		} catch (ParseException e) {
			Log.e("asd",e.getMessage(),e);
		}
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDesignation() {
		return this.designation;
	}

	public void setDesignation(boolean designation) {
		this.designation = designation;
	}
	
	public void modifyCourses(List<String> courses) //argument is course Ids
	{
		this.alCourses=courses;
		this.parseUser.put("courses", this.alCourses);
		try {
			this.parseUser.save();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<String> getAlCourses() {
		return alCourses;
	}

	public void setAlCourses(List<String> alCourses) {
		this.alCourses = alCourses;
	}
	
	
	
}
