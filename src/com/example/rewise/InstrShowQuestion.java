package com.example.rewise;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class InstrShowQuestion extends Activity implements OnItemClickListener{
	ListView list;
    CustomAdapterQuestions adapter;
    public  InstrShowQuestion CustomListView = null;
    public  ArrayList<Question> CustomListViewValuesArr = new ArrayList<Question>();
    List<ParseObject> ob;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_questionbank);
        CustomListView = this;
        Parse.initialize(this, "d6b9vOQMQh333RxqJwDJzUtTuig6uNy15Lh8SzFf", "6MeNeaoCQsFOEjjFRZLbc8ST1TO3BNMb8hlUGTRK");
        setListData();
         
        Resources res =getResources();
        list = ( ListView )findViewById( R.id.listview2 ); 
         
        adapter=new CustomAdapterQuestions( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );
        
		
	}
	
	ProgressDialog pd;
	 public void initPD() {
			pd=new ProgressDialog(InstrShowQuestion.this);
			pd.setTitle("Please Wait...");
			pd.setMessage("Fetching Questions");
			pd.setIndeterminate(true);
			pd.setCancelable(false);
		}
	
	public void setListData()
    {  
		new RemoteDataTask().execute();
    }

    public void onItemClick(int mPosition)
    {
        Question tempValues = ( Question ) CustomListViewValuesArr.get(mPosition);
        Toast.makeText(getApplicationContext(),
                tempValues.getTitle()
                  ,
                Toast.LENGTH_SHORT)
        .show();
    }
    
	 private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		 
	        @Override
	        protected void onPreExecute() {
	        	super.onPreExecute();
	        	initPD();
	        	pd.show();
	        }
	 
	        @Override
	        protected Void doInBackground(Void... params) {
	            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Questions");
	            query.orderByDescending("_created_at");
	            try {
	                ob = query.find();
	            } catch (ParseException e) {
	                Log.e("Error", e.getMessage());
	                e.printStackTrace();
	            }
	            return null;
	        }
	 
	        @SuppressLint("SimpleDateFormat")
			@Override
	        protected void onPostExecute(Void result) {
	            for (ParseObject question : ob) {
	            	final Question sched = new Question();
	                sched.setCategory(question.get("Category").toString());
	                sched.setTitle(question.get("Question").toString());
//	                SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy, kk:mm");
//	            	try {
//						tempchange.endtime=format.parse(quiz.get("EndTime").toString());
//					} catch (java.text.ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	            	try {
//						tempchange.starttime=format.parse(quiz.get("StartTime").toString());
//					} catch (java.text.ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
	            	//tempchange.isTimed=Boolean.parseBoolean(quiz.get("isTimed").toString());
	                CustomListViewValuesArr.add( sched );
	            }
	            adapter.notifyDataSetChanged();
	            pd.dismiss();
	            Log.e("as", "The End!");
	            
	        }
	    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.itemPlus)
		{
			startActivity(new Intent(this,CreateQuestion.class));
		}
		return super.onOptionsItemSelected(item);
	}

}
