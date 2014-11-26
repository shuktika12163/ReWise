package com.example.livequiz;

import com.example.rewise.R;
import com.example.rewise.R.id;
import com.example.rewise.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AttemptScoreActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.myscore);
		int score=getIntent().getIntExtra("Score", 0);
		int max=getIntent().getIntExtra("Max", 0);
		TextView tvv=(TextView)findViewById(R.id.tvAbove);
		tvv.setText(String.valueOf(score));
		tvv=(TextView)findViewById(R.id.tvBelow);
		tvv.setText(String.valueOf(max));
		super.onCreate(savedInstanceState);
	}
	
}
