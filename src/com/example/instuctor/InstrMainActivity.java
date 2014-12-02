package com.example.instuctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rewise.Course;
import com.example.rewise.CourseManagementFragment;
import com.example.rewise.NavBarAdapter;
import com.example.rewise.R;
import com.example.rewise.User;
import com.example.rewise.globalVariables;
import com.example.student.StuCourseStatsScreen;
import com.example.student.StuQuizStatsScreen;
import com.example.tobedeleted.Constants;
import com.parse.Parse;

public class InstrMainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    /*
     * Constants file is being used only as a substitute for parse
     * Manan remove both of them when integrating with Parse
     */
    private SharedPreferences sharedPreferences;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mnavdrawerTitles;
    public static List<Course> courseobjects;
    public static User user;
    public static boolean once=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instr_main);
        Parse.initialize(this, "d6b9vOQMQh333RxqJwDJzUtTuig6uNy15Lh8SzFf", "6MeNeaoCQsFOEjjFRZLbc8ST1TO3BNMb8hlUGTRK");
        Intent view = getIntent();
		String email_id = view.getStringExtra("email_id");
		user=new User(email_id,true);
		globalVariables.designation=true;
		courseobjects=Course.downloadAllCoursesFromDB();
		for(Course each: courseobjects)
		{
			each.setSelected(false);
			for(String each2 : user.getAlCourses())
			{
				if(each.getCode().equals(each2))
					each.setSelected(true);
			}
		}
		/*courseobjects.get(0).setSelected(true);*/
		for(Course each: courseobjects)
		{
			each.downloadQuizzes();
		}
		once=true;
		globalVariables.UID=email_id;
		List<String> userCourses=new ArrayList<String>();
		for(Course course:courseobjects)
			if(course.isSelected())
				userCourses.add(course.getCode());
		InstrMainActivity.user.modifyCourses(userCourses);
		Toast.makeText(InstrMainActivity.this, "Email is "+ email_id, Toast.LENGTH_SHORT).show();
		/*
		 * Use Email to get Courses from Parse and put it into courses
		 */
        mTitle = mDrawerTitle = getTitle();
        mnavdrawerTitles = getResources().getStringArray(R.array.navdrawers_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        sharedPreferences=getSharedPreferences("myprefs", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("I", true)){
	        Constants.set();
	        sharedPreferences.edit().putBoolean("I", false);
        }
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        
        mDrawerList.setAdapter(new NavBarAdapter(this,mnavdrawerTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayUseLogoEnabled(false);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.instr_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()) {
       
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    public void createQuiz(View v){
    	startActivity(new Intent(getApplicationContext(),InstrCreateQuizScreen.class));
    }
    
    public void createQuestion(View v){
    	startActivity(new Intent(getApplicationContext(),InstrCreateQuestionScreen.class));
    }
    
    private void selectItem(int position) {
    	Fragment fragment = new navdrawerFragment();
    	if (position==0){
    		fragment=new InstrHomePageFragment();
    	}
    	if(position==1){
    		fragment= new InstructorProfileFragment();
    	}
    	else if(position==2){
    		fragment=new InstrCourseStatsOverviewFragment();
    	}
    	else if(position==3){
    		fragment = new InstrQuestionBankFragment();
    	}
    	else if(position==4){
    		fragment=new CourseManagementFragment();
    	}
    	else if(position==5){
    		finish();
    	}
        // update the main content by replacing fragments
        
        Bundle args = new Bundle();
        args.putInt(navdrawerFragment.ARG_navdrawer_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mnavdrawerTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a navdrawer
     */
    public static class navdrawerFragment extends Fragment {
        public static final String ARG_navdrawer_NUMBER = "navdrawer_number";

        public navdrawerFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_navdrawer, container, false);
            int i = getArguments().getInt(ARG_navdrawer_NUMBER);
            String navdrawer = getResources().getStringArray(R.array.navdrawers_array)[i];

            int imageId = getResources().getIdentifier(navdrawer.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(navdrawer);
            return rootView;
        }
    }
    
}
