package com.example.rewise;

import java.util.ArrayList;

import com.parse.ParseObject;

public class Question {
	String _id;
	String title;
	ArrayList<String> options;
	boolean isSingle;
	ArrayList<Integer> correct;
	boolean isInDB;
	
	public Question()
	{
		this.title="";
		this.options=new ArrayList<String>();
		this.isSingle=true;
		this.correct=new ArrayList<Integer>();
		this.isInDB=false;
	}
	
	public Question(boolean isSingle)
	{
		this();
		this.isSingle=isSingle;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public ArrayList<String> getOptions()
	{
		return options;
	}
	
	public void setOptions(ArrayList<String> options)
	{
		this.options = options;
	}
	
	public boolean isSingle()
	{
		return isSingle;
	}
	
	public void setSingle(boolean isSingle)
	{
		this.isSingle = isSingle;
	}
	
	public ArrayList<Integer> getCorrectAnswers()
	{
		return correct;
	}
	
	public void setCorrectAnswer(ArrayList<Integer> correct)
	{
		this.correct = correct;
	}
	
	public boolean addCorrectAnswer(int pos)
	{
		if(!this.correct.contains(pos))
		{
			this.correct.add(pos);
			return true;
		}
		return false;
	}
	
	public boolean removeCorrectAnswer(int pos)
	{
		return this.correct.remove((Object)pos);
	}
	
	public boolean addOption(String option)
	{
		if(!this.options.contains(option))
		{
			this.options.add(option);
			return true;
		}
		return false;
	}
	
	public String getOption(int pos)
	{
		return this.options.get(pos);
	}
	
	public boolean deleteOption(String str)
	{
		return this.options.remove(str);
	}
	
	public void deleteAllOptions()
	{
		this.options.clear();
	}
	
	public void uploadToDB()
	{
		ParseObject obj = new ParseObject("Questions");
		obj.put("Question", this.title);
		obj.put("isSingle", this.isSingle);
		obj.put("Correct", this.correct);
		obj.put("Options", this.options);
		obj.saveInBackground();
		this.isInDB=true;
	}
	
	
}
