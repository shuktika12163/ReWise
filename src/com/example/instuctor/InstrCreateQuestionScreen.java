package com.example.instuctor;

import java.util.ArrayList;

import com.example.rewise.R;
import com.example.rewise.Utility;
import com.example.rewise.R.id;
import com.example.rewise.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class InstrCreateQuestionScreen extends Activity implements OnItemLongClickListener{
	
	
	private void changeoption(final int o){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Change Option "+(o+1));
		
		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);
		final String opt="";
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				alOptions.set(o, value.toString());
				adapter.notifyDataSetChanged();
				Utility.setListViewHeightBasedOnChildren(lv);
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				
			}
		});

		alert.show();
		
	}
	private Spinner coursename;
	private EditText explanation; 
	private EditText statement;
	private ListView lv;
	ArrayList<String> alOptions;
	ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_question);
		coursename=(Spinner)findViewById(R.id.courses);
		statement=(EditText)findViewById(R.id.statement);
		explanation=(EditText)findViewById(R.id.explanation);
		alOptions=new ArrayList<String>();
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, alOptions);
		lv=(ListView)findViewById(R.id.lvCreateQ);
		lv.setAdapter(adapter);
		Utility.setListViewHeightBasedOnChildren(lv);  
		lv.setOnItemLongClickListener(this);
		
	}
		
	public void onPlus(View v)
	{
		alOptions.add("Option "+(adapter.getCount()+1));
		adapter.notifyDataSetChanged();
		Utility.setListViewHeightBasedOnChildren(lv);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		changeoption(position);
		return false;
	}
	


	
}