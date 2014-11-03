package com.example.rewise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity {
	Button btn_instr_signup, btn_stu_signup;
	EditText et_username, et_password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		//INITIALIZING VIEW FIELDS
        btn_instr_signup=(Button)findViewById(R.id.btn_instr_signup);
        btn_stu_signup=(Button)findViewById(R.id.btn_stu_signup);
        et_username= (EditText)findViewById(R.id.et_username);
        et_password= (EditText)findViewById(R.id.et_password);
        
        //ONCLICK LISTENERS
        
        btn_instr_signup.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent i = new Intent(getApplicationContext(), InstrMainActivity.class); 
        		startActivity(i);
        	}
        });
        
        btn_stu_signup.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		Intent i = new Intent(getApplicationContext(), InstrMainActivity.class); //StuMainActivity
        		startActivity(i);
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
