package com.example.rewise;

import java.util.ArrayList;

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

public class CustomAdapter extends BaseAdapter   implements OnClickListener {
          
         private Activity activity;
         private ArrayList<?> data;
         private static LayoutInflater inflater=null;
         public Resources res;
         Quiz tempValues=null;
         int i=0;
          
         public CustomAdapter(Activity a, ArrayList<?> d,Resources resLocal) {
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
          
         public static int geticon(String category){
        	 if(category.compareTo("Networking")==0){
        		 return R.drawable.networks;
        	 }
        	 else if(category.compareTo("DataBase")==0){
        		 return R.drawable.database;
        	 }else{
        		 return R.drawable.software;
        	 }
         }
         
         public static class ViewHolder{
             public TextView text;
             public TextView text1;
             public ImageView img;
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
                 holder.text.setText( tempValues.getCode());
                 holder.text1.setText( tempValues.getName());
                 holder.img.setImageResource(geticon(tempValues.getCategory()));
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