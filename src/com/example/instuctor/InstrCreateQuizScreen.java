package com.example.instuctor;

import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.rewise.Quiz;
import com.example.rewise.R;
import com.example.rewise.globalVariables;
 
@SuppressLint("ResourceAsColor")
public class InstrCreateQuizScreen extends Activity{
	private TimePicker timePicker1;
	private int duration; 
	private int hour;
	private int minute;
	private int currentYear;
    private int currentMonth;
    private int currentDay;
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;
	
    private SeekBar seekduration;
    private TextView setduration;
    private TextView settime;
	private TextView setdate;
	private Button timebutton;
	private Button datebutton;
	private EditText quizname;
	private EditText quizcode;
	private Spinner sp;
	
	String[] str= {"CSE400","ECO302","ECE222","CSE121","CSE200","CSE535"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_quiz);
		seekduration=(SeekBar)findViewById(R.id.durationseekBar);
		seekduration.setMax(12);
		seekduration.setProgress(2);
		setduration=(TextView)findViewById(R.id.duration);
		settime=(TextView)findViewById(R.id.time);
		setdate=(TextView)findViewById(R.id.date);
		timebutton=(Button)findViewById(R.id.timebutton);
		datebutton=(Button)findViewById(R.id.datebutton);
		quizname=(EditText)findViewById(R.id.nameofquiz);
		quizcode=(EditText)findViewById(R.id.quizcode);
		sp=(Spinner)findViewById(R.id.coursecode);
		sp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str));
		final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        datebutton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);
            }
        });
        timebutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
        seekduration.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				setduration.setTextSize(40);
				setduration.setTextColor(getResources().getColor(R.color.ourBlue));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				setduration.setTextSize(45);
				setduration.setTextColor(Color.WHITE);
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				duration=progress*5;
				setduration.setText(String.valueOf(progress*5)+" mins");
			}
		});
	}
	
	protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this, SettingDate, currentYear,
                    currentMonth, currentDay);
        case TIME_DIALOG_ID:
            return new TimePickerDialog(this, SettingTime, hour, minute, false);
        }
        return null;
    }
	
	private DatePickerDialog.OnDateSetListener SettingDate = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            setdate.setText((month + 1) + "/" + day + "/" + year);
        }
    };
    

    private TimePickerDialog.OnTimeSetListener SettingTime = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hours, int minutes) {
            settime.setText(hours + ":" + minutes );
        }
    };

    @SuppressWarnings("deprecation")
	public void createQuiz(View v)
    {
    	Quiz quiz=new Quiz();
    	quiz.setCID(sp.getSelectedItem().toString());
    	quiz.setCode(quizcode.getText().toString());
    	Date dt=new Date();
    	String[] s=setdate.getText().toString().split("/");
    	Log.d("asd",s[0]+" "+s[1]+ " "+s[2]);
    	dt.setMonth(Integer.parseInt(s[0])-1);
    	dt.setDate(Integer.parseInt(s[1]));
    	dt.setYear(Integer.parseInt(s[2])-1900);
    	s=settime.getText().toString().split(":");
    	dt.setHours(Integer.parseInt(s[0]));
    	dt.setMinutes(Integer.parseInt(s[1]));
    	dt.setSeconds(0);
    	quiz.setStarttime(dt);
    	quiz.setDuration(0, duration);
    	quiz.setName(quizname.getText().toString());
    	quiz.setTimed(true);
    	quiz.uploadToDB();
    	globalVariables.quiz=quiz;
    	startActivity(new Intent(this,InstrSelectQuestionScreen.class));
    }
    
}
