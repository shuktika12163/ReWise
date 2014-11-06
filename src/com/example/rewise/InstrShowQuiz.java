package com.example.rewise;

import java.text.SimpleDateFormat;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class InstrShowQuiz extends Activity implements OnItemClickListener{
	ListView list;
    CustomAdapter adapter;
    public  InstrShowQuiz CustomListView = null;
    public  ArrayList<Quiz> CustomListViewValuesArr = new ArrayList<Quiz>();
    List<ParseObject> ob;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quizzes);
         
        CustomListView = this;
         
        Parse.initialize(this, "d6b9vOQMQh333RxqJwDJzUtTuig6uNy15Lh8SzFf", "6MeNeaoCQsFOEjjFRZLbc8ST1TO3BNMb8hlUGTRK");
        setListData();
         
        Resources res =getResources();
        list = ( ListView )findViewById( R.id.listview );  // List defined in XML ( See Below )
         
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );
		
	}
	
	ProgressDialog pd;
	 public void initPD() {
			pd=new ProgressDialog(InstrShowQuiz.this);
			pd.setTitle("Please Wait...");
			pd.setMessage("Fetching Quizzes");
			pd.setIndeterminate(true);
			pd.setCancelable(false);
		}
	
	public void setListData()
    {  
		new RemoteDataTask().execute();
    }

    public void onItemClick(int mPosition)
    {
        Quiz tempValues = ( Quiz ) CustomListViewValuesArr.get(mPosition);
        Toast.makeText(getApplicationContext(),
                tempValues.getCode()
                  ,
                Toast.LENGTH_SHORT)
        .show();
    }
	 private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
	        @Override
	        protected void onPreExecute() {
	        	initPD();
	        	pd.show();
	        }
	 
	        @Override
	        protected Void doInBackground(Void... params) {
	        	CustomListViewValuesArr.clear();
	            CustomListViewValuesArr.addAll(Quiz.downloadAllQuizzesFromDB());
	            return null;
	        }
	 
			@Override
	        protected void onPostExecute(Void result) {
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
			startActivity(new Intent(this,CreateQuiz.class));
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
