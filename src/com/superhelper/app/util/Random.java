package com.superhelper.app.util;

import com.superhelper.app.R;

public class Random {
	private static java.util.Random random=new java.util.Random();
	private static int[] bg={
		R.color.color1,
		R.color.color2,
		R.color.color3,
		R.color.color4,
		R.color.color5,
		R.color.color6,
	};
	public static int getBg(){
		return bg[random.nextInt(6)];
	}
}
