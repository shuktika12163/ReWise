package com.example.rewise;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AttemptQuestionFragment extends Fragment implements OnItemClickListener {
	
	Question q;
	int me;
	TextView tv;
	TextView tvE;
	ListView lv;
	OptionAdapter adapter;
	boolean isAttempted;
	boolean runNow=false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		me=getArguments().getInt("qNo");
		q=AttemptQuizActivity.getQuestion(me);
		adapter=new OptionAdapter(getActivity(), R.layout.option,q.getOptions());
		for(int i=0;i<adapter.getCount();i++)
		{
			if(q.getCorrectAnswers().contains(i))
				adapter.corr[i]=true;
			else
				adapter.corr[i]=false;
		}
		adapter.isViewing=false;
		adapter.tagP1=me+":";
		isAttempted=false;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.attemptquestion, container, false);
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		lv=(ListView)getView().findViewById(R.id.lvOptions);
		tv=(TextView)getView().findViewById(R.id.tvQuestion);
		tvE=(TextView)getView().findViewById(R.id.tvExplanation);
		//tvE.setText(q.explanation);
		tv.setText(q.getTitle());
		//lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		checkAttempt();
	}
	
	public void checkAttempt()
	{
		if(isAttempted==false)
		{
			isAttempted=true;
			AttemptQuizActivity.incrementAttempted();
		}
	}
	
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		if(isVisibleToUser)
		{
			runNow=true;
			AttemptQuizActivity.curFrag=this;
		}
		else
		{
			if(runNow)
			{
				runNow=false;
				sendForChecking();
			}
		}
		super.setUserVisibleHint(isVisibleToUser);
	}
	
	public void sendForChecking()
	{
		ArrayList<Integer> alChecked=new ArrayList();
		for(int i=0;i<adapter.getCount();i++)
		{
			if(adapter.isChecked[i])
				alChecked.add(i);
		}
		//Log.d("asd",me+": "+adapter.corr+" ## "+alChecked);
		AttemptQuizActivity.checkAnswer(me, alChecked);
	}
	
}
