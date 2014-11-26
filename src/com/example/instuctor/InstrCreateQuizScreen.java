package com.example.instuctor;

import java.util.Calendar;

import com.example.rewise.R;
import com.example.rewise.R.color;
import com.example.rewise.R.id;
import com.example.rewise.R.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
 
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

}
