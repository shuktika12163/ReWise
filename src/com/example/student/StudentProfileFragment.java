package com.example.student;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rewise.R;
import com.example.rewise.globalVariables;

/*
 * Show a List view of all the Courses with some overview Statistics
 * Use the InstrCourseStats class and the InstrCourseStatsOverviewAdapter
 * Have a small button on the List item for detailed statistics
 * On Clicking on a Particular Course Open the InstrQuizOverView Activity
 */
public class StudentProfileFragment extends Fragment{
	StuCourseStatsOverviewAdapter adapter;
	ListView lv;
	TextView email;
	ImageView im;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fragment_profile, container, false);
		
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		email=(TextView)getView().findViewById(R.id.email);
		im=(ImageView) getView().findViewById(R.id.header);
		im.setImageResource(R.drawable.student);
		email.setText(globalVariables.UID);
	}
}
