package com.superhelper.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private static final String COURSE_TABLE="create table Course ("
			+"id integer primary key autoincrement,"
			+"name text,"
			+ "day_of_week text,"
			+ "start_section	integer,"
			+ "end_section integer,"
			+ "teacher text,"
			+ "room text,"
			+ "flag text,"
			+ "start_week integer,"
			+ "end_week integer)";
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(COURSE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
