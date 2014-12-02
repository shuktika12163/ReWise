package com.example.student;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rewise.R;


public class StuQuizStatsOverviewAdapter extends BaseAdapter{
          
         private Activity activity;
         private ArrayList<?> data;
         private static LayoutInflater inflater=null;
         StuQuizStats tempValues=null;
         int i=0;
          
         public StuQuizStatsOverviewAdapter(Activity a, ArrayList<?> d) {
                 activity = a;
                 data= d;
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
             public TextView code;
             public TextView quiz;
             public TextView attendence;
             public TextView average;
             public TextView highest;
             public LinearLayout back;
         }
      
         @SuppressLint("InflateParams")
		public View getView(int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             ViewHolder holder;
              
             if(convertView==null){
                 vi = inflater.inflate(R.layout.list_item_quiz_stats_overview, null);
                 holder = new ViewHolder();
                 holder.quiz = (TextView) vi.findViewById(R.id.firstLine);
                 holder.code=(TextView) vi.findViewById(R.id.secondLine);
                 holder.attendence=(TextView) vi.findViewById(R.id.attendence);
                 holder.average=(TextView) vi.findViewById(R.id.average);
                 holder.highest=(TextView) vi.findViewById(R.id.highest);
                 holder.back=(LinearLayout) vi.findViewById(R.id.mainback);
                 vi.setTag( holder );
             }
             else 
                 holder=(ViewHolder)vi.getTag();
              
             if(data.size()<=0)
             {
                 holder.quiz.setText("Sorry!");
                 holder.code.setText("No Quizzes Found!");
                 holder.attendence.setText("-");
                 holder.average.setText("-");
                 holder.highest.setText("-");
                 holder.back.setBackgroundResource(R.drawable.defaultheader);
             }
             else
             {
                 tempValues=null;
                 tempValues = ( StuQuizStats ) data.get( position );
                 holder.quiz.setText(tempValues.name);
                 holder.code.setText(tempValues.code);
                 holder.back.setBackgroundResource(tempValues.icon);
                 holder.attendence.setText(String.valueOf(tempValues.attendence));
                 holder.average.setText(String.valueOf(tempValues.classaverage));
                 holder.highest.setText(String.valueOf(tempValues.highest));
             }
             return vi;
         }
     }