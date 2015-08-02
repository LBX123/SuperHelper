package com.superhelper.app.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class HttpUtil {
	private static AsyncHttpClient client=new AsyncHttpClient();
	
	public static final String Host="210.33.60.8";
	
	public static final String URL_LOGIN="http://210.33.60.8/default2.aspx";
	public static final String URL_COURSE="http://210.33.60.8/xskbcx.aspx?xh=201205070626&xm=林炳希&gnmkdm=N121603";
	public static final String URL_REFER="http://210.33.60.8/xs_main.aspx?xh=201205070626";
	public static String Button1="";
	public static String hidPdrs="";
	public static String hidsc="";
	public static String lbLanguage="";
	public static String RadioButtonList1="学生";
	public static String __VIEWSTATE="dDwyODE2NTM0OTg7Oz429KFYILujcPfYvN3IBCq9kLaO+A==";
	public static String __VIEWSTATEGENERATOR="92719903";
	public static String TextBox2 = "";
    public static String txtSecretCode = "";
    public static String txtUserName = "";
	public static String __EVENTARGUMENT="";
	public static String __EVENTTARGET="";
	public static String xnd="";
	public static String xqd="";
	public static Set<String> years=new LinkedHashSet<String>();
	public static Set<String> semesters=new LinkedHashSet<String>();
    static{
    	client.setTimeout(10000);
//    	client.addHeader("Host", Host);
    	client.addHeader("Referer", URL_LOGIN);
    }
    /**
     * 
     * @param url
     * @param res
     */
    public static void get(String url,AsyncHttpResponseHandler res){
    	client.get(url, res);
    }
    public static void get(String url,TextHttpResponseHandler res){
    	client.get(url, res);
    }
    
    public static void get(String url,RequestParams params,AsyncHttpResponseHandler res){
    	client.get(url, params,res);
    }
    
    public static void get(String url,BinaryHttpResponseHandler binary){
    	client.get(url, binary);
    }
    
    public static void post(String url,AsyncHttpResponseHandler res){
    	client.post(url, res);
    }
    
    public static void post(String url,RequestParams params,AsyncHttpResponseHandler res){
    	client.post(url, params,res);
    }
    
    public static void post(String url,RequestParams params,TextHttpResponseHandler txt){
    	client.post(url, params,txt);
    }
    
    public static void post(String url,BinaryHttpResponseHandler binary){
    	client.post(url, binary);
    }
    
    public  static AsyncHttpClient getClient(){
    	return client;
    }
    
    public static RequestParams getLoginRequestParams(){
    	RequestParams params = new RequestParams();
    	params.add("__VIEWSTATE", __VIEWSTATE);
    	params.add("__VIEWSTATEGENERATOR", __VIEWSTATEGENERATOR);
    	params.add("Button1", Button1);
    	params.add("hidPdrs", hidPdrs);
    	params.add("hidsc", hidsc);
    	params.add("lbLanguage", lbLanguage);
    	params.add("RadioButtonList1", RadioButtonList1);
    	params.add("TextBox2", TextBox2);
    	params.add("txtSecretCode", txtSecretCode);
    	params.add("txtUserName", txtUserName);
    	return params;
    }
}
