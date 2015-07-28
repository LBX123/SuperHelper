package com.superhelper.app.util;

import java.util.ArrayList;
import java.util.List;

public class WeekParser {
	public static int parse(String week){
		if(week.equals("周一")){
			return 1;
		}else if(week.equals("周二")){
			return 2;
		}else if(week.equals("周三")){
			return 3;
		}else if(week.equals("周四")){
			return 4;
		}else if(week.equals("周五")){
			return 5;
		}else if(week.equals("周六")){
			return 6;
		}else if(week.equals("周日")){
			return 7;
		}
	 return 0;
	}
	public static List<String>getWeekList(int length){
		List<String> list=new ArrayList<String>();
		for (int i = 1; i <= length; i++) {
			list.add("第"+i+"周");
		}
		return list;
	}
}
