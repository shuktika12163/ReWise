package com.example.rewise;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavBarAdapter  extends ArrayAdapter<String> {
	
Context context;
private String[] TextValue;

public NavBarAdapter(Context context, String[] mnavdrawerTitles) {
    super(context, R.layout.drawer_list_item, mnavdrawerTitles);
    this.context = context;
    this.TextValue= mnavdrawerTitles;

}


@Override
public View getView(int position, View coverView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.drawer_list_item,parent, false);
    TextView text1 = (TextView)rowView.findViewById(R.id.text1);
    ImageView img1 = (ImageView)rowView.findViewById(R.id.img1);
    text1.setText(TextValue[position]);
    if(position==0){
    	img1.setImageResource(R.drawable.home);
    }
    else if(position==1){
    	img1.setImageResource(R.drawable.profile);
    }
	else if(position==2){
		img1.setImageResource(R.drawable.stats);
	}
	else if(position==3){
		img1.setImageResource(R.drawable.archive);
	}
	else if(position==4){
		img1.setImageResource(R.drawable.course);
	}
	else{
		img1.setImageResource(R.drawable.logout);
	}
    
    return rowView;

}

}