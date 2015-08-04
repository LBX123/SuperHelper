package com.superhelper.app.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.superhelper.app.R;
import com.superhelper.app.db.DBOperation;
import com.superhelper.app.model.Course;
import com.superhelper.app.util.HttpUtil;
import com.superhelper.app.util.ParseHtml;

import android.app.Activity;
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectSemesterActivity extends BaseActivity implements OnClickListener{
	private ImageButton btnBack;
	private Spinner spinnerYear;
	private Spinner spinnerSemester;
	private ArrayAdapter<String> yearAdapter;
	private ArrayAdapter<String> semesterAdapter;
	private Button btnLogin;
	private SharedPreferences pf;
	private Editor edit;
	private static final String  USERNAME="201205070626";
	   private static final String PWD="lin807403827xi";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectsemester_activity);
		initData();
		findViews();
	}
	private void initData() {
		// TODO Auto-generated method stub
		pf=getSharedPreferences("data",MODE_PRIVATE);
	    edit = pf.edit();
	    HttpUtil.years= pf.getStringSet("years", null);
	    HttpUtil.semesters=pf.getStringSet("semesters", null);
	}
	private void findViews() {
		// TODO Auto-generated method stub
		spinnerYear=(Spinner) findViewById(R.id.spinner_year);
		spinnerSemester=(Spinner) findViewById(R.id.spinner_semester);
		btnBack=(ImageButton) findViewById(R.id.btn_back);
		btnBack.setOnClickListener(this);
		btnLogin=(Button) findViewById(R.id.login);
		btnLogin.setOnClickListener(this);
		yearAdapter=new ArrayAdapter<String>(this, R.layout.spinner_item1,new ArrayList<String>(HttpUtil.years));
		yearAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item1);
		spinnerYear.setAdapter(yearAdapter);
		spinnerYear.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				// TODO Auto-generated method stub
				System.out.println("year="+yearAdapter.getItem(pos));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		semesterAdapter=new ArrayAdapter<String>(this, R.layout.spinner_item2, new ArrayList<String>(HttpUtil.semesters));
		semesterAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item2);
		spinnerSemester.setAdapter(semesterAdapter);
		spinnerSemester.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				// TODO Auto-generated method stub
				System.out.println("semester="+semesterAdapter.getItem(pos));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:
			jumpTo(CourseActivity.class);
			break;
		case R.id.login:
			
				doLogin();
		
			
			
//			doUpdate();
			break;
		default:
			break;
		}
	}
	public void doLogin(){
			showProgress(SelectSemesterActivity.this);
			HttpUtil.post(HttpUtil.URL_LOGIN, HttpUtil.getLoginRequestParams(), new TextHttpResponseHandler("GBK") {
				
				@Override
				public void onSuccess(int status, Header[] headers, String response) {
					// TODO Auto-generated method stub
					closeProgress();
					Toast.makeText(getApplicationContext(),"登录成功！", Toast.LENGTH_SHORT).show();
					doParse();
				}
				
				@Override
				public void onFailure(int status, Header[] headers, String response, Throwable error) {
					// TODO Auto-generated method stub
					closeProgress();
					Toast.makeText(SelectSemesterActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
				}
			});
	}
	public void doParse(){
		showProgress(SelectSemesterActivity.this);
		HttpUtil.getClient().addHeader("Referer", HttpUtil.URL_REFER);
		HttpUtil.get(HttpUtil.URL_COURSE, new TextHttpResponseHandler("GBK") {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String res) {
				// TODO Auto-generated method stub
				ParseHtml.parseViewstate(res);
				closeProgress();
				Toast.makeText(SelectSemesterActivity.this, "解析成功！", Toast.LENGTH_SHORT).show();
				doUpdate();
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String res, Throwable arg3) {
				// TODO Auto-generated method stub
				System.out.println(res);
			}
		});
	}
	public void doUpdate(){
		showProgress(SelectSemesterActivity.this);
		HttpUtil.getClient().removeHeader("Referer");
		HttpUtil.getClient().addHeader("Referer",HttpUtil.URL_REFER);//非常重要
		RequestParams params=new RequestParams();
		params.add("__EVENTARGUMENT", "");
		params.add("__EVENTTARGET", "xnd");
		params.add("__VIEWSTATE", HttpUtil.__VIEWSTATE);
		params.add("__VIEWSTATEGENERATOR", HttpUtil.__VIEWSTATEGENERATOR);
		params.add("xnd", spinnerYear.getSelectedItem().toString());
		params.add("xqd", spinnerSemester.getSelectedItem().toString());
		System.out.println(params);
		HttpUtil.post(HttpUtil.URL_COURSE,params, new TextHttpResponseHandler("GBK") {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String res) {
				// TODO Auto-generated method stub
				List<Course> list = ParseHtml.parseCourse(res);
				DBOperation db = DBOperation.getInstance(SelectSemesterActivity.this);
				db.deleteAllData();
				db.saveCourses(list);
				closeProgress();
				Toast.makeText(SelectSemesterActivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
				jumpTo(CourseActivity.class);
				finish();
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String res, Throwable arg3) {
				// TODO Auto-generated method stub
				closeProgress();
				System.out.println(arg3);
				System.out.println(res);
			}
		});
	}
}
