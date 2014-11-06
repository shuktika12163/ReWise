package com.example.rewise;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.R.string;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class Question {
	String _id;
	String title;
	String category;
	ArrayList<String> options;
	boolean isSingle;
	ArrayList<Integer> correct;
	boolean isInDB;
	String explanation;
	static ArrayList<Question> al;
	static boolean once=false;
	
	public Question()
	{
		if(!once)
		{
			al=new ArrayList<Question>();
			once=true;
		}
		this._id="";
		this.title="";
		this.category="";
		this.options=new ArrayList<String>();
		this.isSingle=true;
		this.correct=new ArrayList<Integer>();
		this.isInDB=false;
		this.explanation="";
		al.add(this);
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
	
	public void setCategory(String category)
	{
		this.category = category;
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
	public String getCategory()
	{
		return this.category;
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
	
	public void setAllAsCorrectAnswers()
	{
		this.correct.clear();
		for(int i=0;i<this.options.size();i++)
			this.correct.add(i);
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
	
	public boolean uploadToDB()
	{
		
//		if(this.isInDB)
//			return true;
		final ParseObject obj = new ParseObject("Questions");
		obj.put("Question", this.title);
		obj.put("isSingle", this.isSingle);
		obj.put("Correct", this.correct);
		obj.put("Options", this.options);
		obj.put("Explanation", this.explanation);
		try{
			Log.d("asd","started saving");
			obj.save();
			Log.d("asd","done saving");
		}
		catch(Exception e){
			Log.d("asd","err saving");
			return false;
		}
		this.isInDB=true;
		this._id=obj.getObjectId();
		return true;
	}
	
	public void ParseQuestion(ParseObject parseObject)
	{
		this._id=parseObject.getObjectId();
		this.title=parseObject.getString("Question");
		this.isSingle=parseObject.getBoolean("isSingle");
		this.category=parseObject.getString("Category");
		this.options=(ArrayList<String>) parseObject.get("Options");
		this.correct=(ArrayList<Integer>) parseObject.get("Correct");
		this.explanation=parseObject.getString("Explanation");
	}
	
	public static List<Question> downloadQsFromDB(List<String> lIds)
	{
		List<Question> lQs=new ArrayList<Question>();
		ParseQuery<ParseObject> queryGetQs = new ParseQuery<ParseObject>("Questions");
		queryGetQs.whereContainedIn("objectId", lIds);
		
		ArrayList<ParseObject> gettingQsParse;
		try {
			gettingQsParse = (ArrayList<ParseObject>) queryGetQs.find();
			for(ParseObject parseObject:gettingQsParse)
			{
				Question question=new Question();
				question.ParseQuestion(parseObject);
				lQs.add(question);
			}
			return lQs;
		} catch (ParseException e) {
		}
		
		return null;
	}
	
}