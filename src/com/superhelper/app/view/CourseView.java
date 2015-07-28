package com.superhelper.app.view;

import com.superhelper.app.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

public class CourseView extends Button{
	private int startSection;
	private int endSection;
	private String week;
	private int startWeek;
	private int endWeek;
	public CourseView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CourseView);
		startSection=typedArray.getInt(R.styleable.CourseView_startSection, 0);
		endSection=typedArray.getInt(R.styleable.CourseView_endSection, 0);
		week=typedArray.getString(R.styleable.CourseView_week);
		startWeek=typedArray.getInt(R.styleable.CourseView_startWeek,0);
		endWeek=typedArray.getInt(R.styleable.CourseView_endWeek,0);
		typedArray.recycle();
	}

	public CourseView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public CourseView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}


	public int getStartSection() {
		return startSection;
	}

	public void setStartSection(int startSection) {
		this.startSection = startSection;
	}

	public int getEndSection() {
		return endSection;
	}

	public void setEndSection(int endSection) {
		this.endSection = endSection;
	}

	public int getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}

	public int getEndWeek() {
		return endWeek;
	}

	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
}
