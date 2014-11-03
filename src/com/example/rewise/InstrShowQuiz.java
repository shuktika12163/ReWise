package com.example.rewise;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class InstrShowQuiz extends Activity{
	ListView lv;
	List<String> al;
	ArrayAdapter<String> adapter;
	CustomAdapter ca;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_quizzes);
		lv=(ListView)findViewById(R.id.listview);
		lv.setOnItemClickListener((OnItemClickListener) this);
		al=new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("d");
		al.add("e");
		al.add("f");
		al.add("g");
		al.add("h");
		al.add("i");
		al.add("j");
		al.add("k");
		al.add("l");
		al.add("m");
		al.add("n");
		ca=new CustomAdapter(this, R.layout.list, al);
		//adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.list, al);
		lv.setAdapter(ca);
	}
	
	public void onItemClick(AdapterView<?> arg0, final View view, int arg2, long arg3) {
		final String item = (String) arg0.getItemAtPosition(arg2);
		Toast.makeText(getApplicationContext(), item, 1).show();
		
		// TODO Auto-generated method stub
		
	}
	
}
