package com.example.rewise;

import java.util.ArrayList;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Temp extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		setContentView(R.layout.temp);
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "d6b9vOQMQh333RxqJwDJzUtTuig6uNy15Lh8SzFf", "6MeNeaoCQsFOEjjFRZLbc8ST1TO3BNMb8hlUGTRK");
	}
	
	Question q;
	public void onClick(View v)
	{
		q=new Question(true);
		q.setTitle("Why did we select Software Engineering?");
		q.addOption("Peer Pressure");
		q.addOption("Lack of choices");
		q.addOption("Under an impression that we'll actually learn something");
		q.addOption("Something to write in our CV!");
		q.addOption("Boredom");
		q.addCorrectAnswer(0);
		if(q.uploadToDB())
			Toast.makeText(Temp.this, "done "+q._id, 0).show();
		else
			Toast.makeText(Temp.this, "not done "+q._id, 0).show();
	}
	
	Question q2;
	public void onClick2(View v){
		q2=new Question(false);
		q2.setTitle("What does the fox say?");
		q2.addOption("Ring-ding-ding-ding-dingeringeding!");
		q2.addOption("Wa-pa-pa-pa-pa-pa-pow!");
		q2.addOption("Hatee-hatee-hatee-ho!");
		q2.addOption("Chacha-chacha-chacha-chow!");
		q2.addOption("A-hee-ahee ha-hee!!");
		q2.setAllAsCorrectAnswers();
		q2.explanation="In which century are u living?";
		q2.uploadToDB();
	}
	
	Quiz quiz;
	public void onClick3(View v)
	{
		quiz=new Quiz();
		quiz.setCode("DMB1");
		quiz.setTimed(false);
		quiz.setCategory("cat");
		quiz.setName("DUMB SET 1");
		quiz.uploadToDB();
	}
	
	public void onClick4(View v)
	{
		Quiz q=new Quiz();
		q._id="zpBlhPnQJU";
		q.downloadQsFromDB();
		for(Question qq:q.getQuestions())
			Toast.makeText(this, qq.getTitle(), 0).show();
		
		
//		Log.d("asd", "entered connect");
//		quiz.addQuestion(q);
//		quiz.addQuestion(q2);
//		quiz.uploadQsToDB();
	}

}
