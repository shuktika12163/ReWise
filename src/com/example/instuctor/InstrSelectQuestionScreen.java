package com.example.instuctor;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rewise.Question;
import com.example.rewise.Quiz;
import com.example.rewise.R;
import com.example.rewise.globalVariables;
import com.parse.Parse;

public class InstrSelectQuestionScreen extends Activity implements OnItemClickListener{
	ListView list;
    InstrSelectQuestionAdapter adapter;
    public  ArrayList<Question> CustomListViewValuesArr = new ArrayList<Question>();
    List<Question> ob;
    ImageView fab;
    
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_questions);
        Parse.initialize(this, "d6b9vOQMQh333RxqJwDJzUtTuig6uNy15Lh8SzFf", "6MeNeaoCQsFOEjjFRZLbc8ST1TO3BNMb8hlUGTRK");
        setListData();
        list = ( ListView )findViewById( R.id.selectquestions); 
        adapter=new InstrSelectQuestionAdapter(this, CustomListViewValuesArr);
        list.setAdapter( adapter );
	}
	
	 ProgressDialog pd;
	 public void initPD() {
			pd=new ProgressDialog(this);
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
                tempValues.getQuestion()
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
	            ob=Question.downloadAllQsFromDB();
	            return null;
	        }
	 
	        @SuppressLint("SimpleDateFormat")
			@Override
	        protected void onPostExecute(Void result) {
	            for (Question question : ob) {
	            	/*final Question sched = new Question();
	                sched.setCategory(question.get("Category").toString());
	                sched.setQuestion(question.get("Question").toString());*/
	                CustomListViewValuesArr.add(question);
	            }
	            adapter.notifyDataSetChanged();
	            pd.dismiss();
	            Log.e("as", "The End!");
	            
	        }
	    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}
	
	@Override
	public void onBackPressed() {
		Quiz quiz=globalVariables.quiz;
		for(Question question:ob)
		{
			if(question.isSelected())
			{
				quiz.addQuestion(question);
			}
		}
		quiz.uploadQsToDB();
		super.onBackPressed();
	}
	
}
