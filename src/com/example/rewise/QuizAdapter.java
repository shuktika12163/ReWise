package com.example.rewise;

import java.util.ArrayList;
import java.util.Date;

import com.example.instuctor.InstrShowQuiz;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizAdapter extends BaseAdapter implements OnClickListener {
          
         private Activity activity;
         private ArrayList<?> data;
         private static LayoutInflater inflater=null;
         public Resources res;
         Quiz tempValues=null;
         int i=0;
          
         public int getStatusIcon(Date d,Date e){
        	 java.util.Date date = new java.util.Date();
        	 long time = date.getTime();
        	 if(d.compareTo(date)>0){
        		 return R.drawable.upcoming;
        	 }
        	 else{
        		 if(e.compareTo(date)<0){
        			 return R.drawable.past;
        		 }
        		 else{
        			 return R.drawable.live;
        		 }
        	 }
         }
         
         public QuizAdapter(Activity a, ArrayList<?> d,Resources resLocal) {
                 activity = a;
                 data= d;
                 res = resLocal;
                 inflater = ( LayoutInflater )activity.
                                              getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              
         }
      
         public int getCount() {
             if(data.size()<=0)
                 return 1;
             return data.size();
         }
      
         public Object getItem(int position) {
             return position;
         }
      
         public long getItemId(int position) {
             return position;
         }
          
         public static class ViewHolder{
             public TextView text;
             public TextView text1;
             public ImageView img;
             public ImageView status;
         }
      
         @SuppressLint("InflateParams")
		public View getView(int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             ViewHolder holder;
              
             if(convertView==null){
                 vi = inflater.inflate(R.layout.list, null);
                 holder = new ViewHolder();
                 holder.text = (TextView) vi.findViewById(R.id.firstLine);
                 holder.text1=(TextView) vi.findViewById(R.id.secondLine);
                 holder.img=(ImageView) vi.findViewById(R.id.imageView1);
                 holder.status=(ImageView) vi.findViewById(R.id.imageView2);
                 vi.setTag( holder );
             }
             else 
                 holder=(ViewHolder)vi.getTag();
              
             if(data.size()<=0)
             {
                 holder.text.setText("Loading...");
                 holder.text1.setText("We are fetching your data.");
             }
             else
             {
                 tempValues=null;
                 tempValues = ( Quiz ) data.get( position );
                 holder.text.setText( tempValues.getName());
                 holder.text1.setText( tempValues.getStarttime().toString());
                 Date d=tempValues.getStarttime();
                 Date e=tempValues.getEndtime();
                 holder.status.setImageResource(getStatusIcon(d, e));
                 holder.img.setImageResource(R.drawable.right);
                 vi.setOnClickListener(new OnItemClickListener( position ));
             }
             return vi;
         }
          
         @Override
         public void onClick(View v) {
                 Log.v("CustomAdapter", "=====Row button clicked=====");
         }
          
         private class OnItemClickListener  implements OnClickListener{          
             private int mPosition;
             OnItemClickListener(int position){
                  mPosition = position;
             }
              
             @Override
             public void onClick(View arg0) {
               InstrShowQuiz sct = (InstrShowQuiz)activity;
                 sct.onItemClick(mPosition);
             }              
         }  
     }