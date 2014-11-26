package com.example.instuctor;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rewise.Question;
import com.example.rewise.QuestionAdapter;
import com.example.rewise.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class InstrQuestionBankFragment extends Fragment implements OnItemClickListener{
	ListView list;
    QuestionAdapter adapter;
    public  InstrQuestionBankFragment CustomListView = null;
    public  ArrayList<Question> CustomListViewValuesArr = new ArrayList<Question>();
    List<ParseObject> ob;
    ImageView fab;
    
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        
        
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fragment_show_questionbank, container, false);
		
		return v;
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		CustomListView = this;
        Parse.initialize(getActivity(), "d6b9vOQMQh333RxqJwDJzUtTuig6uNy15Lh8SzFf", "6MeNeaoCQsFOEjjFRZLbc8ST1TO3BNMb8hlUGTRK");
        setListData();
         
        Resources res =getResources();
        list = ( ListView )getView().findViewById( R.id.listview2 ); 
         
        adapter=new QuestionAdapter( getActivity(), CustomListViewValuesArr);
        list.setAdapter( adapter );
        
        fab=(ImageView)getView().findViewById(R.id.add_button);
		fab.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                	 fab.setImageDrawable(getResources().getDrawable(R.drawable.plus_light_pressed));
                    break;
                }
                case MotionEvent.ACTION_CANCEL:{
                	 fab.setImageDrawable(getResources().getDrawable(R.drawable.plus_light));
                    break;
                }
                case MotionEvent.ACTION_UP:{
                	fab.setImageDrawable(getResources().getDrawable(R.drawable.plus_light));
                    break;
                }
                }
                return false;
            }
        });
	}
	
	ProgressDialog pd;
	 public void initPD() {
			pd=new ProgressDialog(getActivity());
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
        Toast.makeText(getActivity().getApplicationContext(),
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
	                sched.setQuestion(question.get("Question").toString());
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
	
}
