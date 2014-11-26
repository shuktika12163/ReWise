package com.example.instuctor;
import java.util.ArrayList;

import android.app.Fragment;
import android.content.SharedPreferences;
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
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.rewise.Course;
import com.example.rewise.CourseQuizAdapter;
import com.example.rewise.Quiz;
import com.example.rewise.R;
import com.example.tobedeleted.Constants;

public class InstrHomePageFragment extends Fragment implements OnItemClickListener{
	
	ExpandableListView lv;
	CourseQuizAdapter adapter;
	ImageView fab;
		
	private ArrayList<Course> parents;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
        InstrMainActivity im= (InstrMainActivity)getActivity();
        
        for (Course i: Constants.CourseObjects)
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
            adapter = new CourseQuizAdapter(parents,getActivity().getApplicationContext());
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
		// TODO Auto-generated method stub
		
	}
}