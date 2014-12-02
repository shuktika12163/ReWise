package com.example.student;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;

import com.example.livequiz.AttemptQuizActivity;
import com.example.rewise.Course;
import com.example.rewise.Quiz;
import com.example.rewise.R;
import com.example.rewise.globalVariables;
import com.parse.Parse;

public class StuHomePageFragment extends Fragment implements OnItemClickListener{
	
	ExpandableListView lv;
	StuCourseQuizAdapter adapter;
	ImageView fab;
		
	private ArrayList<Course> parents;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Parse.initialize(getActivity(), "d6b9vOQMQh333RxqJwDJzUtTuig6uNy15Lh8SzFf", "6MeNeaoCQsFOEjjFRZLbc8ST1TO3BNMb8hlUGTRK");
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fragment_homepage, container, false);
		
		return v;
		
	}
	
	/**
     * here should come your data service implementation
     * @return
     */
    private ArrayList<Course> populate()
    {
        // Creating ArrayList of type parent class to store parent class objects
        final ArrayList<Course> list = new ArrayList<Course>();
        StuMainActivity im= (StuMainActivity)getActivity();
        List<Course> CourseObjects=StuMainActivity.courseobjects;//Constants.CourseObjects
        for (Course i: CourseObjects)
        {
            if (i.isSelected()==true){
            	list.add(i);
            }
        }
        return list;
    }
	    
    private void loadHosts(final ArrayList<Course> newParents)
    {
        if (newParents == null)
            return;
         
        parents = newParents;
         
        if (lv.getExpandableListAdapter() == null)
        {
            adapter = new StuCourseQuizAdapter(parents,getActivity().getApplicationContext());
            lv.setAdapter(adapter);
        }
        else
        {
            adapter.notifyDataSetChanged();
        }  
    }
    
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		fab=(ImageView)getView().findViewById(R.id.add_button);
		fab.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                	 fab.setImageDrawable(getResources().getDrawable(R.drawable.plus_light_pressed));
                    break;
                }
                case MotionEvent.ACTION_CANCEL:{
                	 fab.setImageDrawable(getResources().getDrawable(R.drawable.plus_light));
                    break;
                }
                case MotionEvent.ACTION_UP:{
                	fab.setImageDrawable(getResources().getDrawable(R.drawable.plus_light));
                    break;
                }
                }
                return false;
            }
        });
		lv=(ExpandableListView)getView().findViewById(R.id.updates);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		lv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final Quiz selected = (Quiz) adapter.getChild(
                        groupPosition, childPosition);
				globalVariables.quiz=selected;
				startActivity(new Intent(getActivity(),AttemptQuizActivity.class));
				return true;
			}
		});
		
		Resources res = this.getResources();
        Drawable devider = res.getDrawable(R.drawable.listgrad);
        lv.setGroupIndicator(null);
        lv.setDivider(devider);
        lv.setChildDivider(devider);
        lv.setDividerHeight(1);
        registerForContextMenu(lv);
        final ArrayList<Course> contents = populate();
        loadHosts(contents);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(StuMainActivity.once)
		{
			StuMainActivity.once=false;
			return;
		}
		for(Course each: StuMainActivity.courseobjects)
		{
			each.downloadQuizzes();
		}
		adapter.notifyDataSetChanged();
	}
	
	
}