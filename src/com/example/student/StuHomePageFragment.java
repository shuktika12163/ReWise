package com.example.student;
import java.util.ArrayList;

import com.example.rewise.Course;
import com.example.rewise.CourseQuizAdapter;
import com.example.rewise.Quiz;
import com.example.rewise.R;
import com.example.rewise.R.drawable;
import com.example.rewise.R.id;
import com.example.rewise.R.layout;

import android.app.Fragment;
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

public class StuHomePageFragment extends Fragment implements OnItemClickListener{
	
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
    private ArrayList<Course> buildDummyData()
    {
        // Creating ArrayList of type parent class to store parent class objects
        final ArrayList<Course> list = new ArrayList<Course>();
        for (int i = 1; i < 4; i++)
        {
            //Create parent class object
            final Course parent = new Course();
             
              // Set values in parent class object
                  if(i==1){
                      parent.setName("Course" + i);
                      parent.setCode("Parent 0");
                      parent.setChildren(new ArrayList<Quiz>());
                       
                      // Create Child class object
                      final Quiz child = new Quiz();
                        child.setName("Quiz" + i);
                        child.setCode("Child 0");
                         
                        //Add Child class object to parent class object
                        parent.getChildren().add(child);
                    }
                   else if(i==2){
                       parent.setName("Course" + i);
                       parent.setCode("Parent 1");
                       parent.setChildren(new ArrayList<Quiz>());
                        
                       final Quiz child = new Quiz();
                        child.setName("Quiz" + i);
                        child.setCode("Child 0");
                        parent.getChildren().add(child);
                       final Quiz child1 = new Quiz();
                        child1.setName("Quiz" + i);
                        child1.setCode("Child 1");
                        parent.getChildren().add(child1);
                     }
                   else if(i==3){
                       parent.setName("Course" + i);
                       parent.setCode("Parent 2");
                       parent.setChildren(new ArrayList<Quiz>());
                        
                       final Quiz child = new Quiz();
                        child.setName("Quiz" + i);
                        child.setCode("Child 0");
                        parent.getChildren().add(child);
                       final Quiz child1 = new Quiz();
                        child1.setName("Quiz" + i);
                        child1.setCode("Child 1");
                        parent.getChildren().add(child1);
                      final Quiz child2 = new Quiz();
                        child2.setName("Quiz" + i);
                        child2.setCode("Child 2");
                        parent.getChildren().add(child2);
                       final Quiz child3 = new Quiz();
                        child3.setName("Quiz" + i);
                        child3.setCode("Child 3");
                        parent.getChildren().add(child3);
                      }
            //Adding Parent class object to ArrayList        
            list.add(parent);
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
        final ArrayList<Course> dummyList = buildDummyData();
        loadHosts(dummyList);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
}