package com.example.livequiz;

import java.util.ArrayList;

import com.example.rewise.OptionAdapter;
import com.example.rewise.Question;
import com.example.rewise.R;
import com.example.rewise.R.id;
import com.example.rewise.R.layout;


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
import android.widget.ExpandableListView;
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
		setQ(AttemptQuizActivity.getQuestion(me));
		adapter=new OptionAdapter(getActivity(), R.layout.option,getQ().getOptions());
		for(int i=0;i<adapter.getCount();i++)
		{
			if(getQ().getCorrectAnswers().contains(i))
				adapter.getCorr()[i]=true;
			else
				adapter.getCorr()[i]=false;
		}
		adapter.setViewing(false);
		adapter.setTagP1(me+":");
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
		setTvE((TextView)getView().findViewById(R.id.tvExplanation));
		//tvE.setText(q.explanation);
		tv.setText(getQ().getQuestion());
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
			AttemptQuizActivity.setCurFrag(this);
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
			if(adapter.getIsChecked()[i])
				alChecked.add(i);
		}
		//Log.d("asd",me+": "+adapter.corr+" ## "+alChecked);
		AttemptQuizActivity.checkAnswer(me, alChecked);
	}

	public TextView getTvE() {
		return tvE;
	}

	public void setTvE(TextView tvE) {
		this.tvE = tvE;
	}

	public Question getQ() {
		return q;
	}

	public void setQ(Question q) {
		this.q = q;
	}
	
}
