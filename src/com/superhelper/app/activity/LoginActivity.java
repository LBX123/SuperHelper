package com.superhelper.app.activity;


import java.net.URLEncoder;

import org.apache.http.Header;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.superhelper.app.R;
import com.superhelper.app.util.HttpUtil;


public class LoginActivity extends BaseActivity{
   private EditText username;
   private EditText pwd;
   private Button login;
   private static final String  USERNAME="201205070626";
   private static final String PWD="lin807403827xi";
   private SharedPreferences preferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		username=(EditText) findViewById(R.id.username);
		pwd=(EditText) findViewById(R.id.pwd);
		login=(Button) findViewById(R.id.login);
		preferences=getSharedPreferences("config", MODE_PRIVATE);
		boolean logined=preferences.getBoolean("logined", false);
		if (logined) {
			Intent intent=new Intent(this,CourseActivity.class);
			startActivity(intent);
			finish();
			return ;
		}
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    showProgress(LoginActivity.this);
				HttpUtil.txtUserName=USERNAME;
				HttpUtil.TextBox2=PWD;
				HttpUtil.post(HttpUtil.URL_LOGIN, HttpUtil.getLoginRequestParams(), new TextHttpResponseHandler("GBK") {
					
					@Override
					public void onSuccess(int status, Header[] headers, String response) {
						// TODO Auto-generated method stub
						closeProgress();
						Toast.makeText(getApplicationContext(),"登录成功！", Toast.LENGTH_SHORT).show();
						Editor edit = preferences.edit();
						edit.putBoolean("logined", true);
						edit.commit();
						jumpTo(MenuActivity.class);
					}
					
					@Override
					public void onFailure(int status, Header[] headers, String response, Throwable error) {
						// TODO Auto-generated method stub
						closeProgress();
						Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}
}
