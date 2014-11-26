package com.example.rewise;

import com.example.tobedeleted.Constants;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
		ArrayAdapter<Course> adapter = new CourseSelectionAdapter(getActivity(), Constants.CourseObjects);
		lv=(ListView)getView().findViewById(R.id.preferences);
		lv.setAdapter(adapter);
		super.onActivityCreated(savedInstanceState);
	}
}
