package com.example.rewise;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instuctor.InstrMainActivity;
import com.example.student.StuMainActivity;
import com.google.android.gms.common.AccountPicker;


public class MainLoginActivity extends Activity {
	
	static final int REQUEST_CODE_PICK_ACCOUNT_INSTR = 1000;
	static final int REQUEST_CODE_PICK_ACCOUNT_STU = 1001;
	ImageView btn_instr_login, btn_stu_login, btn_login, btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        
        //INITIALIZING VIEW FIELDS
        btn_instr_login=(ImageView)findViewById(R.id.btn_instr_login);
        btn_stu_login=(ImageView)findViewById(R.id.btn_stu_login);
        
        
        //ONCLICK LISTENERS
        
        btn_instr_login.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		
        		if (isNetworkAvailable() == true) {
        			pickUserAccount(REQUEST_CODE_PICK_ACCOUNT_INSTR);
        		} else {
        			Toast.makeText(MainLoginActivity.this, "No Network Service!",
        					Toast.LENGTH_SHORT).show();
        		}
        		
        	}
        });
        
        btn_instr_login.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                	btn_instr_login.setImageDrawable(getResources().getDrawable(R.drawable.instructor_pressed));
                    break;
                }
                case MotionEvent.ACTION_CANCEL:{
                	btn_instr_login.setImageDrawable(getResources().getDrawable(R.drawable.instructor));
                    break;
                }
                case MotionEvent.ACTION_UP:{
                	btn_instr_login.setImageDrawable(getResources().getDrawable(R.drawable.instructor));
                    break;
                }
                }
                return false;
            }
        });
        
        btn_stu_login.setOnClickListener(new OnClickListener()
        {
        	@Override
        	public void onClick(View v){
        		if (isNetworkAvailable() == true) {
        			pickUserAccount(REQUEST_CODE_PICK_ACCOUNT_STU);
        		} else {
        			Toast.makeText(MainLoginActivity.this, "No Network Service!",
        					Toast.LENGTH_SHORT).show();
        		}
        	}
        });
       
        btn_stu_login.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                	 btn_stu_login.setImageDrawable(getResources().getDrawable(R.drawable.student_pressed));
                    break;
                }
                case MotionEvent.ACTION_CANCEL:{
                	 btn_stu_login.setImageDrawable(getResources().getDrawable(R.drawable.student));
                    break;
                }
                case MotionEvent.ACTION_UP:{
                	btn_stu_login.setImageDrawable(getResources().getDrawable(R.drawable.student));
                    break;
                }
                }
                return false;
            }
        });
        
    }


    private void pickUserAccount(int requestCode) {
        String[] accountTypes = new String[]{"com.google"};
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                accountTypes, false, null, null, null, null);
        startActivityForResult(intent, requestCode);
    }
    
    public boolean isNetworkAvailable() {
    	ConnectivityManager cm = (ConnectivityManager) (MainLoginActivity.this).getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = cm.getActiveNetworkInfo();
    	if (networkInfo != null && networkInfo.isConnected()) {
    		Log.e("Network Testing", "***Available***");
    		return true;
    	}
    	Log.e("Network Testing", "***Not Available***");
    	return false;
    }



    String mEmail; // Received from newChooseAccountIntent(); passed to getToken()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == REQUEST_CODE_PICK_ACCOUNT_INSTR || requestCode == REQUEST_CODE_PICK_ACCOUNT_STU) {
    		// Receiving a result from the AccountPicker
    		if (resultCode == RESULT_OK) {
    			mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
    			if(requestCode == REQUEST_CODE_PICK_ACCOUNT_INSTR) {
        			Intent i = new Intent(getApplicationContext(), InstrMainActivity.class);
        			i.putExtra("email_id", mEmail);
        			startActivity(i);
        			//Toast.makeText(MainLoginActivity.this, "mEmail is "+mEmail, Toast.LENGTH_SHORT).show();
        			// With the account name acquired, go get the auth token
        			//getUsername(); //create this function if needed
    			} else {
    				Intent i = new Intent(getApplicationContext(), StuMainActivity.class);
        			i.putExtra("email_id", mEmail);
        			startActivity(i);
    			}
    		} else if (resultCode == RESULT_CANCELED) {
    			// The account picker dialog closed without selecting an account.
    			// Notify users that they must pick an account to proceed.
    			Toast.makeText(this, "Pick an account!", Toast.LENGTH_SHORT).show();
    		}
    	}
    	// Later, more code will go here to handle the result from some exceptions...
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_login, menu);
        return true;
    }
}
