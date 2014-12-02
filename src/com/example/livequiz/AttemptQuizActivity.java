package com.example.livequiz;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import com.example.instuctor.InstrMainActivity;
import com.example.rewise.Course;
import com.example.rewise.Question;
import com.example.rewise.Quiz;
import com.example.rewise.R;
import com.example.rewise.globalVariables;
import com.example.rewise.R.id;
import com.example.rewise.R.layout;
import com.example.student.StuMainActivity;
import com.example.tobedeleted.MockStatModel;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class AttemptQuizActivity extends FragmentActivity implements OnItemClickListener, OnClickListener {
	
	static TextView tv,tvTime;
	static ArrayList<Question> alQ;
	static ArrayList<int[]> alResponses;
	static int countAttempted=0;
	private static AttemptQuestionFragment curFrag;
	static boolean[] isCorrect;
	ImageButton ib;
	int countScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.attempquiz);
		init();
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}
	
	Quiz quiz;
	public void init()
	{
		quiz=globalVariables.quiz;
		globalVariables.QID=quiz.getCode();//hardcoding QID
		alQ=new ArrayList<Question>();
		quiz.downloadQsFromDB();
		alQ=quiz.getQuestions();//initQ();
		alResponses=new ArrayList<int[]>(); for(int i=0;i<alQ.size();i++){alResponses.add(new int[]{});}
		countAttempted=-1;
		NUM_PAGES=alQ.size();
		frags=new AttemptQuestionFragment[NUM_PAGES];
		isCorrect=new boolean[NUM_PAGES];
		hasSubmitted=false;
		initViews();
		initDates();
		initAlert();
	}
	
	public void initViews()
	{
		  // Instantiate a ViewPager and a PagerAdapter.
		tv=(TextView)findViewById(R.id.tvAttempted);
		tvTime=(TextView)findViewById(R.id.tvTime);
		incrementAttempted();
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setOffscreenPageLimit(NUM_PAGES-1);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        //mPager.setOnPageChangeListener(this);
        ImageButton ib=(ImageButton)findViewById(R.id.btnSubmit);
        ib.setOnClickListener(this);
	}
	
	Question q;
	public void initQ()
	{
		
		q=new Question(true);
		q.setQuestion("Why did we select Software Engineering?");
		q.addOption("Peer Pressure");
		q.addOption("Lack of choices");
		q.addOption("Under an impression that we'll actually learn something");
		q.addOption("Something to write in our CV!");
		q.addOption("Boredom");
		q.addCorrectAnswer(0);
		q.setExplanation("We are Stuuupiiiiidddd");
		alQ.add(q);
		
		q=new Question(false);
		q.setQuestion("What does the fox say?");
		q.addOption("Ring-ding-ding-ding-dingeringeding!");
		q.addOption("Wa-pa-pa-pa-pa-pa-pow!");
		q.addOption("Hatee-hatee-hatee-ho!");
		q.addOption("Chacha-chacha-chacha-chow!");
		q.addOption("A-hee-ahee ha-hee!!");
		q.setAllAsCorrectAnswers();
		q.setExplanation("In which century are u living?");
		alQ.add(q);

		q=new Question(true);
		q.setQuestion("What is the API level of Android Watch?");
		q.addOption("19");
		q.addOption("20");
		q.addOption("21");
		q.addOption("22");
		q.addCorrectAnswer(1);
		q.setExplanation("20 - 4.4W");
		alQ.add(q);

		q=new Question(true);
		q.setQuestion("Which Pokemon do we all like");
		q.addOption("Bulbasaur");
		q.addOption("Charmander");
		q.addOption("Squirtle");
		q.addOption("Pikachu");
		q.addOption("Togepi");
		q.addCorrectAnswer(3);
		q.setExplanation(";)");
		alQ.add(q);
	}
	
	public static void checkAnswer(int position, ArrayList<Integer> myAnswers)
	{
		ArrayList<Integer> correctAnswers=alQ.get(position).getCorrectAnswers();
		alResponses.set(position, convertArrayListToArray(myAnswers));
		if(correctAnswers.size()!=myAnswers.size())
		{
			isCorrect[position]=false;
			return;
		}
		Collections.sort(myAnswers);
		Collections.sort(correctAnswers);
		for(int i=0;i<correctAnswers.size();i++)
		{
			if(correctAnswers.get(i)!=myAnswers.get(i))
			{
				isCorrect[position]=false;
				return;
			}
		}
		isCorrect[position]=true;
	}
	
	public static Question getQuestion(int position)
	{
		return alQ.get(position);
	}

	public static void incrementAttempted()
	{
		countAttempted++;
		tv.setText(countAttempted+" of "+alQ.size()+" attempted");
	}

	/**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private static ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
        	if(handlerNeeded)
        	{
        		submitQuiz();
        		return;
        	}
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    
    AttemptQuestionFragment[] frags;

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
        	Fragment f=new AttemptQuestionFragment();
			Bundle b = new Bundle();
			b.putInt("qNo", position);
			f.setArguments(b);
			frags[position]=(AttemptQuestionFragment) f;
        	return f;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
        
    }

    
	@Override
	public void onClick(View v) 
	{
		if(v.getId()==R.id.btnSubmit)
		{
			//handlerNeeded=false;
			alert.show();
		}
	}
	
	boolean hasSubmitted=false;
	public void submitQuiz()
	{
		handlerNeeded=false;
		getCurFrag().sendForChecking();
		countScore=0;
		for(int i=0;i<isCorrect.length;i++)
			if(isCorrect[i])
				countScore++;
		Toast.makeText(getApplicationContext(), "Correct: "+countScore, 0).show();
//		Bundle bun=new Bundle();
//		Intent intent=new Intent(this, AttemptScoreActivity.class);
//		intent.putExtra("Score", countScore);
//		intent.putExtra("Max", alQ.size());
//		startActivity(intent);
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.myscore);
		dialog.setTitle("Your Score");

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.tvAbove);
		text.setText(countScore+"");
		text = (TextView) dialog.findViewById(R.id.tvBelow);
		text.setText(alQ.size()+"");
		dialog.setCancelable(true);

		Button dialogButton = (Button) dialog.findViewById(R.id.btnOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
		hasSubmitted=true;
		tvTime.setVisibility(View.GONE);
		markAnswers();
		//onPageSelected(mPager.getCurrentItem());
		mPager.setCurrentItem(0);
		uploadStats();
	}
	
	
	public void uploadStats()
	{
		ArrayList<MockStatModel> alStat=new ArrayList<MockStatModel>();
		for(int i=0;i<alQ.size();i++)
		{
			MockStatModel mod=new MockStatModel();
			mod.setUID(globalVariables.UID);
			mod.setQID(alQ.get(i).get_id());
			mod.setQuizName(quiz.getName());
			mod.setCID(quiz.getCID());
			mod.setCourseName(getName(quiz.getCID()));
			mod.setZID(quiz.getCode());
			mod.setCorrect(convertArrayListToArray(alQ.get(i).getCorrectAnswers()));
			mod.setResponse(alResponses.get(i));
			mod.setDesignation(false);
			mod.setCorrect(isCorrect[i]);
			alStat.add(mod);
		}
		MockStatModel.uploadStats(alStat);
	}

	private String getName(String cid) {
		ArrayList<Course> courses=(ArrayList<Course>) StuMainActivity.courseobjects;
		for(Course each: courses)
		{
			if (cid.equals(each.getCode()))
				return each.getName();
		}
		return null;
	}

	Date starttime;
	Date endtime;
	Handler h;
	boolean handlerNeeded;
	public void initDates()
	{
		handlerNeeded=true;
		starttime=Calendar.getInstance().getTime();
		long t1=starttime.getTime();long t2=quiz.getEndtime().getTime();
		long d=t2-t1; d/=1000;
		setDuration(0, 0, (int)d);
		setRemainingTime();
		h=new Handler();
		h.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				setRemainingTime();
				if(!handlerNeeded)
					return;
				if(hms.hr>0 || hms.min>0 || hms.sec>0)
					h.postDelayed(this, 1000);
				else
					submitQuiz();
			}
		}, 1000);
	}
	
	public void setDuration(int hrs, int min, int sec)
	{
		long start=starttime.getTime(); //in milliseconds
		long duration=hrs*60+min;
		duration*=60;
		duration+=sec;
		duration*=1000;
		endtime=new Date(start+duration);
	}
	
	public void setRemainingTime()
	{
		HMS temp=getRemainingTime();
		tvTime.setText(temp.toString());
	}
	
	public HMS getRemainingTime()
	{
		hms=new HMS();
		long cur=Calendar.getInstance().getTimeInMillis();
		long end=endtime.getTime();
		long delta=end-cur;
		if(delta<0)
		{
			hms.hr=hms.min=hms.sec=0;
			return hms;
		}
		delta/=1000;
		hms.sec=(int) (delta%60);
		hms.min=(int) (delta/60);
		hms.hr=hms.min/60;
		hms.min=hms.min%60;
		return hms;
	}
	
	HMS hms;
	public class HMS{
		int hr,min,sec;
		@Override
		public String toString() {return hr+"h:"+min+"m:"+sec+"s";}
	}
	
	@Override
	protected void onStop() {
		handlerNeeded=false;
		super.onStop();
	}
	
	AlertDialog.Builder alert;
	public void initAlert()
	{
		alert = new AlertDialog.Builder(this);
		alert.setTitle("Confirmation");
		alert.setMessage("Do you want to Submit the Quiz?");
		alert.setCancelable(false);
		alert.setIcon(android.R.drawable.ic_dialog_alert);
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				submitQuiz();
			 
		}});
		alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
	}
	
	public void markAnswers()
	{
		for(AttemptQuestionFragment f : frags)
		{
			f.tvE.setText("Explanation: "+f.q.getExplanation());
			f.adapter.setViewing(true);
			f.adapter.notifyDataSetChanged();
		}
	}


	public static AttemptQuestionFragment getCurFrag() {
		return curFrag;
	}


	public static void setCurFrag(AttemptQuestionFragment curFrag) {
		AttemptQuizActivity.curFrag = curFrag;
	}

//	@Override
//	public void onPageScrollStateChanged(int arg0) {	}
//	@Override
//	public void onPageScrolled(int arg0, float arg1, int arg2) {	}
//
//	@Override
//	public void onPageSelected(int arg0) {
//		if(hasSubmitted)
//		{
//			if(curFrag.adapter.isViewing==false)
//			{
//				curFrag.tvE.setText("Explanation: "+curFrag.q.explanation);
//				curFrag.adapter.isViewing=true;
//				curFrag.adapter.notifyDataSetChanged();
//			}
//		}
//	}
	
	public static int[] convertArrayListToArray(ArrayList<Integer> al)
	{
		int[] x=new int[al.size()];
		for(int i=0;i<al.size();i++)
		{
			x[i]=al.get(i);
		}
		return x;
	}
	
}
