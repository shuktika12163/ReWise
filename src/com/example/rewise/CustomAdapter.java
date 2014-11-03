package com.example.rewise;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final ArrayList al;
	
	public CustomAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		this.context=context;
		this.al=(ArrayList)objects;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int i;
		//if(position%2==0)
			i=R.layout.list;
		//else
			//i=R.layout.list2;
		View rowView = li.inflate(i, parent, false);
		TextView tv=(TextView)rowView.findViewById(R.id.firstLine);
		ImageView img=(ImageView)rowView.findViewById(R.id.icon);
		tv.setText(al.get(position).toString());
		if(position%2==0)
			img.setImageResource(android.R.drawable.arrow_up_float);
		else
			img.setImageResource(android.R.drawable.arrow_down_float);
		return rowView;
	}

}
