package com.superhelper.app.util;

public class IntegerParser {
	public static int parse(String str){
		if(str!=null&&!"".equals(str)){
			return Integer.parseInt(str);
		}
		return 0;
	}
}
