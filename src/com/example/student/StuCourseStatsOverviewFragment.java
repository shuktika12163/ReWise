package com.example.student;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rewise.Course;
import com.example.rewise.R;
import com.example.rewise.globalVariables;
import com.example.tobedeleted.Constants;

/*
 * Show a List view of all the Courses with some overview Statistics
 * Use the InstrCourseStats class and the InstrCourseStatsOverviewAdapter
 * Have a small button on the List item for detailed statistics
 * On Clicking on a Particular Course Open the InstrQuizOverView Activity
 */
public class StuCourseStatsOverviewFragment extends Fragment implements OnItemClickListener{
	StuCourseStatsOverviewAdapter adapter;
	ListView lv;
	ArrayList<StuCourseStats> ics;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fragment_course_stats_overview, container, false);
		return v;
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		List<Course> CourseObjects=Course.downloadAllCoursesFromDB();//Constants.CourseObjects;
		ics=new ArrayList();
		for( Course i : CourseObjects){
			if(i.isSelected()){
				StuCourseStats temp=new StuCourseStats(i.getCode());
				ics.add(temp);
			}
		}
		globalVariables.CStuStatistics=ics;
		adapter=new StuCourseStatsOverviewAdapter(getActivity(), ics);
		lv=(ListView)getView().findViewById(R.id.listview3);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		super.onActivityCreated(savedInstanceState);
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(ics.size()!=0){
			getActivity().startActivity(new Intent(getActivity(),StuQuizStatsOverviewScreen.class).putExtra("cid",((StuCourseStats) ics.get(position)).code));
		}
	}
}
