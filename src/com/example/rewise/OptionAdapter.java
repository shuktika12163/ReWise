package com.example.rewise;

import java.util.ArrayList;
import java.util.List;

import com.example.livequiz.AttemptQuizActivity;
import com.example.rewise.R;
import com.example.rewise.R.color;
import com.example.rewise.R.id;
import com.example.rewise.R.layout;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

public class OptionAdapter extends ArrayAdapter<String> implements OnClickListener{
	
	private final Context context;
	private ArrayList<String> al;
	private boolean[] corr;
	private boolean[] isChecked;
	private boolean isViewing=false;
	private String tagP1;
	String tagP2;

	public OptionAdapter(Context context, int resource, List<String> list) {
		super(context, resource);
		this.context=context;
		this.al=(ArrayList) list;
		this.setCorr(new boolean[al.size()]);
		this.setIsChecked(new boolean[al.size()]);
		for(int i=0;i<al.size();i++)
			this.getIsChecked()[i]=false;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = li.inflate(R.layout.option, parent, false);
		CheckBox cb=(CheckBox) rowView.findViewById(R.id.checkBox1);
		String str=(String) al.get(position);
		cb.setText(str);
		tagP2=String.valueOf(position);
		cb.setTag(getTagP1()+tagP2);
		cb.setOnClickListener(this);
		if(isViewing())
		{
			if(this.getIsChecked()[position])
				cb.setChecked(true);
			else
				cb.setChecked(false);
			
			if(this.getCorr()[position])
			{
				cb.setBackgroundColor(getContext().getResources().getColor(R.color.ourGreen));
			}
			else if(cb.isChecked())
			{
				cb.setBackgroundColor(Color.RED);
			}
		}
		else
		{
			cb.setBackgroundColor(Color.BLACK);
		}
		return rowView;
	}
	
	@Override
	public int getCount() {
		return al.size();
	}

	@Override
	public void onClick(View v) {
		String s[]=v.getTag().toString().split(":");
		int temp=Integer.parseInt(s[1]);
		this.getIsChecked()[temp]=((CheckBox)v).isChecked();
		AttemptQuizActivity.getCurFrag().checkAttempt();
	}

	public boolean isViewing() {
		return isViewing;
	}

	public void setViewing(boolean isViewing) {
		this.isViewing = isViewing;
	}

	public boolean[] getCorr() {
		return corr;
	}

	public void setCorr(boolean[] corr) {
		this.corr = corr;
	}

	public String getTagP1() {
		return tagP1;
	}

	public void setTagP1(String tagP1) {
		this.tagP1 = tagP1;
	}

	public boolean[] getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(boolean[] isChecked) {
		this.isChecked = isChecked;
	}

}
