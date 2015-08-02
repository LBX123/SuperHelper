package com.superhelper.app.activity;

import java.util.List;

import com.superhelper.app.R;
import com.superhelper.app.db.DBOperation;
import com.superhelper.app.layout.CourseLayout;
import com.superhelper.app.model.Course;
import com.superhelper.app.util.Random;
import com.superhelper.app.util.WeekParser;
import com.superhelper.app.view.CourseView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;

public class CourseActivity extends BaseActivity implements OnItemSelectedListener,OnClickListener{
	private CourseLayout courseLayout;
	private ScrollView scrollView;
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private List<Course> list;
	private ImageButton btnMore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_activity);
		initView();
	}
	private void initView() {
		// TODO Auto-generated method stub
		 list=DBOperation.getInstance(CourseActivity.this).getCourses();
		courseLayout=(CourseLayout) findViewById(R.id.course_layout);
		scrollView=(ScrollView) findViewById(R.id.scroll_view);
		scrollView.setFillViewport(true);
		spinner=(Spinner) findViewById(R.id.spinner_week);
		btnMore=(ImageButton) findViewById(R.id.btn_more);
		btnMore.setOnClickListener(this);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, WeekParser.getWeekList(25));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		addViews( list,1);
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View view, int pos, long arg3) {
		// TODO Auto-generated method stub
		System.out.println("pos="+pos);
		courseLayout.removeAllViews();
		addViews(list, pos+1);
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	public void addViews(List<Course> list,int currentWeek){
		for (Course course : list) {
			CourseView view=new CourseView(getApplicationContext());
			int startWeek = course.getStartWeek();
			int endWeek = course.getEndWeek();
			if(currentWeek>=startWeek&&currentWeek<=endWeek){
				view.setStartSection(course.getStartSection());
				view.setEndSection(course.getEndSection());
				view.setWeek(course.getDayOfWeek());
				view.setStartWeek(startWeek);
				view.setEndWeek(endWeek);
				view.setText(""+course.getName()+course.getRoom());
				view.setTextSize(12);
				view.setTextColor(Color.WHITE);
				view.setBackgroundResource(Random.getBg());
				view.setGravity(Gravity.CENTER);
				courseLayout.addView(view);
			}	
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_more:
			jumpTo(SelectSemesterActivity.class);
			break;

		default:
			break;
		}
	}
}
