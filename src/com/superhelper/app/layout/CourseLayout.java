package com.superhelper.app.layout;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.superhelper.app.util.WeekParser;
import com.superhelper.app.view.CourseView;

public class CourseLayout extends ViewGroup{
	private List<CourseView> courses=new ArrayList<CourseView>();
	private int width;
	private int height;
	private int sectionWidth;
	private int sectionHeight;
	private int sectionNumber=12;
	private int dayNumber=7;
	private int divideWidth=2;
	private int divideHeight=2;
	public CourseLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		width=getScreenWidth();
		height=dip2px(600);
		divideHeight=dip2px(2);
		divideWidth=dip2px(2);
	}

	public CourseLayout(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public CourseLayout(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
			courses.clear();
			sectionWidth=(width-divideWidth*dayNumber)/dayNumber;
			sectionHeight=(height-divideHeight*sectionNumber)/sectionNumber;
			int count=getChildCount();
			for (int i = 0; i < count; i++) {
				CourseView courseView = (CourseView) getChildAt(i);
				int week=WeekParser.parse(courseView.getWeek());
				int startSection=courseView.getStartSection();
				int endSection=courseView.getEndSection();
				if(endSection==0)
					endSection=startSection;
				int left=(week-1)*sectionWidth+divideWidth*week;
				int right=left+sectionWidth;
				int top=(startSection-1)*sectionHeight+divideHeight*startSection;
				int bottom=top+sectionHeight*(endSection-startSection+1)+divideHeight*(endSection-startSection);
				courseView.layout(left, top, right, bottom);
				courses.add(courseView);
			}
	}
	private int dip2px(float dip){
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip*density+0.5f);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(width, height);
	}
	private int getScreenWidth(){
		WindowManager manager=(WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics.widthPixels;
	}
}
