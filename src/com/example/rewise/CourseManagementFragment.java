package com.example.rewise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.instuctor.InstrMainActivity;
import com.example.student.StuMainActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CourseManagementFragment extends Fragment{
	CourseSelectionAdapter adapter;
	ListView lv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fragment_configure, container, false);
		return v; 
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		ArrayList<Course> CourseObjects;
		if(globalVariables.designation)
			CourseObjects=(ArrayList<Course>) InstrMainActivity.courseobjects;//Constants.CourseObjects;
		else
			CourseObjects=(ArrayList<Course>) StuMainActivity.courseobjects;//Constants.CourseObjects;
		ArrayAdapter<Course> adapter = new CourseSelectionAdapter(getActivity(), CourseObjects);
		lv=(ListView)getView().findViewById(R.id.preferences);
		lv.setAdapter(adapter);
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		List<String> userCourses=new ArrayList<String>();
		
		if(globalVariables.designation)
		{
			for(Course course:InstrMainActivity.courseobjects)
				if(course.isSelected())
					userCourses.add(course.getCode());
			InstrMainActivity.user.modifyCourses(userCourses);
		}
		else
		{
			for(Course course:StuMainActivity.courseobjects)
				if(course.isSelected())
					userCourses.add(course.getCode());
			StuMainActivity.user.modifyCourses(userCourses);
		}
	}
	
}
