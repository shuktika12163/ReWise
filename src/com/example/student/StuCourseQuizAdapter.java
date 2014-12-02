package com.example.student;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rewise.Course;
import com.example.rewise.Quiz;
import com.example.rewise.R;

public class StuCourseQuizAdapter extends BaseExpandableListAdapter{
	private ArrayList<Course> parents;
	public static int ParentClickStatus=-1;
    public static int ChildClickStatus=-1;
    
    private LayoutInflater inflater;

    public StuCourseQuizAdapter(ArrayList<Course> p,Context context)
    {
    	parents=p;
        inflater = LayoutInflater.from(context);
    }
 
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
    
    public int getStatus(Date d,Date e){
    	java.util.Date date = new java.util.Date();
    	long time = date.getTime();
    	if(d.compareTo(date)>0){
    		return 1;
    	}
    	else{
    		if(e.compareTo(date)<0){
    			return -1;
    		}
    		else{
    			return 0;
    		}
    	}
    }
    
    // This Function used to inflate parent rows view
     
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parentView)
    {
    	final Course parent = parents.get(groupPosition);
        convertView = inflater.inflate(R.layout.list_item_course, parentView, false);
        if(parents.size()<=0){
        	((TextView) convertView.findViewById(R.id.firstLine)).setText("It's lonely in here!");
            ((TextView) convertView.findViewById(R.id.secondLine)).setText("Go ahead and select some courses from the Course Management Section!");
        }
        else{
	        ((TextView) convertView.findViewById(R.id.firstLine)).setText(parent.getName());
	        ((TextView) convertView.findViewById(R.id.secondLine)).setText(String.valueOf(parent.getChildren().size())+" Upcoming Quizzes");
	        ((LinearLayout)convertView.findViewById(R.id.mainback)).setBackgroundResource(parent.getBack());
        }
        return convertView;
    }
     
    // This Function used to inflate child rows view
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parentView)
    {
        final Course parent = parents.get(groupPosition);
        final Quiz child = parent.getChildren().get(childPosition);
        if(getStatus(child.getStarttime(), child.getEndtime())<0)
        	return null;
         
        // Inflate childrow.xml file for child rows
        convertView = inflater.inflate(R.layout.list_item_quiz, parentView, false);
         
        // Get childrow.xml file elements and set values
        ((TextView) convertView.findViewById(R.id.firstLine)).setText(child.getName());
        ((TextView) convertView.findViewById(R.id.secondLine)).setText(child.getStarttime().toString());
        ((ImageView) convertView.findViewById(R.id.imageView2)).setImageResource(getStatusIcon(child.getStarttime(), child.getEndtime()));
        return convertView;
    }

     
    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        //Log.i("Childs", groupPosition+"=  getChild =="+childPosition);
        return parents.get(groupPosition).getChildren().get(childPosition);
    }

    //Call when child row clicked
    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        /****** When Child row clicked then this function call *******/
         
        //Log.i("Noise", "parent == "+groupPosition+"=  child : =="+childPosition);
        if( ChildClickStatus!=childPosition)
        {
           ChildClickStatus = childPosition;
        } 
         
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        int size=0;
        if(parents.get(groupPosition).getChildren()!=null)
            size = parents.get(groupPosition).getChildren().size();
        return size;
    }
  
     
    @Override
    public Object getGroup(int groupPosition)
    {
        Log.i("Parent", groupPosition+"=  getGroup ");
         
        return parents.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return parents.size();
    }

    //Call when parent row clicked
    @Override
    public long getGroupId(int groupPosition)
    {
        Log.i("Parent", groupPosition+"=  getGroupId "+ParentClickStatus);
         
        if(groupPosition==2 && ParentClickStatus!=groupPosition){
             
        }
         
        ParentClickStatus=groupPosition;
        if(ParentClickStatus==0)
            ParentClickStatus=-1;
         
        return groupPosition;
    }

    @Override
    public void notifyDataSetChanged()
    {
        // Refresh List rows
        super.notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty()
    {
        return ((parents == null) || parents.isEmpty());
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }
}
