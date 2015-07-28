package com.superhelper.app.activity;

import java.net.URLEncoder;

import org.apache.http.Header;

import com.loopj.android.http.TextHttpResponseHandler;
import com.superhelper.app.R;
import com.superhelper.app.db.DBHelper;
import com.superhelper.app.db.DBOperation;
import com.superhelper.app.util.HttpUtil;
import com.superhelper.app.util.ParseHtml;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends BaseActivity {
	private Button get_courses;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
		get_courses=(Button) findViewById(R.id.get_courses);
		get_courses.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showProgress(MenuActivity.this);
				HttpUtil.getClient().addHeader("Referer", HttpUtil.URL_REFER);
				HttpUtil.get(URLEncoder.encode(HttpUtil.URL_COURSE), new TextHttpResponseHandler("GBK") {
					
					@Override
					public void onSuccess(int arg0, Header[] arg1, String res) {
						// TODO Auto-generated method stub
						DBOperation.getInstance(MenuActivity.this).saveCourses(ParseHtml.parseCourse(res));
						Intent intent=new Intent(MenuActivity.this,CourseActivity.class);
						startActivity(intent);
						closeProgress();
						finish();
						return;
					}
					
					@Override
					public void onFailure(int arg0, Header[] arg1, String res, Throwable arg3) {
						// TODO Auto-generated method stub
						System.out.println(res);
					}
				});
			}
		});
	}
}
