package com.superhelper.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends Activity {
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	public void showProgress(Context context){
		if(progressDialog==null){
		progressDialog=new ProgressDialog(context);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage("加载中...");
		}
		progressDialog.show();
	}
	public void closeProgress(){
		if (progressDialog!=null) {
			progressDialog.dismiss();
		}
	}
	public void jumpTo(Class<? extends BaseActivity> activity){
		Intent intent=new Intent(this,activity);
		startActivity(intent);
		finish();
	}
}
