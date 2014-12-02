package com.example.instuctor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.rewise.Course;
import com.example.rewise.R;
import com.example.rewise.globalVariables;
import com.example.tobedeleted.Constants;
import com.example.tobedeleted.MapCourseToUser;
import com.example.tobedeleted.MapQuizToCourse;

/*
 * Show a List view of all the Courses with some overview Statistics
 * Use the InstrCourseStats class and the InstrCourseStatsOverviewAdapter
 * Have a small button on the List item for detailed statistics
 * On Clicking on a Particular Course Open the InstrQuizOverView Activity
 */
public class InstrQuizStatsOverviewScreen extends Activity implements OnItemClickListener{
	InstrQuizStatsOverviewAdapter adapter;
	ListView lv;
	ImageView fab;
	ArrayList<InstrQuizStats> iqs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_quiz_stats_overview);
		super.onCreate(savedInstanceState);
		iqs=new ArrayList();
		String cid=getIntent().getStringExtra("cid");
		System.out.println("Intent"+cid);
		for( InstrCourseStats i : globalVariables.CStatistics){
			System.out.println("Looking for course in stats!"+String.valueOf(i.getQuizStats().size())+i.code);
			if(i.code.compareTo(cid)==0){
				System.out.println("I found Something!"+String.valueOf(i.getQuizStats().size()));
				iqs=i.getQuizStats();
				break;
			}
		}
		globalVariables.QStatistics=iqs;
		Log.d("new","iqs   len     "+iqs.size());
		adapter=new InstrQuizStatsOverviewAdapter(this, iqs);
		lv=(ListView)findViewById(R.id.listview4);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		fab=(ImageView)findViewById(R.id.add_button);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),InstrCourseStatsScreen.class).putExtra("cid", getIntent().getStringExtra("cid")));
			}
		});
		fab.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                	 fab.setImageDrawable(getResources().getDrawable(R.drawable.stats_light_pressed));
                    break;
                }
                case MotionEvent.ACTION_CANCEL:{
                	 fab.setImageDrawable(getResources().getDrawable(R.drawable.stats_light));
                    break;
                }
                case MotionEvent.ACTION_UP:{
                	fab.setImageDrawable(getResources().getDrawable(R.drawable.stats_light));
                    break;
                }
                }
                return false;
            }
        });
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.e("QuizStatsOverview", String.valueOf(parent.getChildCount()));
		if(iqs.size()!=0){
			startActivity(new Intent(this,InstrQuizStatsScreen.class).putExtra("qid",(iqs.get(position).code)));
		}
		
	}
}
