package com.superhelper.app.activity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.http.Header;

import com.loopj.android.http.TextHttpResponseHandler;
import com.superhelper.app.R;
import com.superhelper.app.util.HttpUtil;
import com.superhelper.app.util.ParseHtml;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
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
			showProgress(getApplicationContext());
			HttpUtil.post(HttpUtil.URL_LOGIN, HttpUtil.getLoginRequestParams(),new TextHttpResponseHandler("GBK") {
				
				@Override
				public void onSuccess(int arg0, Header[] arg1, String res) {
					// TODO Auto-generated method stub
					Toast.makeText(SelectSemesterActivity.this, "登陆成功，获取课表中...", Toast.LENGTH_SHORT).show();
					closeProgress();
				}
				
				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(SelectSemesterActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
				}
			});
			break;
		default:
			break;
		}
	}

}
