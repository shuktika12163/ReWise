package com.example.rewise;

import com.parse.Parse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Temp extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		setContentView(R.layout.temp);
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "d6b9vOQMQh333RxqJwDJzUtTuig6uNy15Lh8SzFf", "6MeNeaoCQsFOEjjFRZLbc8ST1TO3BNMb8hlUGTRK");
	}
	
	public void onClick(View v)
	{
		Question q=new Question(true);
		q.setTitle("Why did we select Software Engineering?");
		q.addOption("Peer Pressure");
		q.addOption("Lack of choices");
		q.addOption("Under an impression that we'll actually learn something");
		q.addOption("Something to write in our CV!");
		q.addOption("Boredom");
		q.addCorrectAnswer(0);
		q.uploadToDB();
	}

}
