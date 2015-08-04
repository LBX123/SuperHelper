package com.superhelper.app.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.superhelper.app.model.Course;

public class DBOperation {
	private static final String  DB_NAME="super_helper";
	private static int VERSION=1;
	private  SQLiteDatabase db;
	private static DBOperation dbOperation;
	private DBOperation(Context context)
	{
		DBHelper helper=new DBHelper(context, DB_NAME, null	, VERSION);
		db=helper.getWritableDatabase();
	}
	public synchronized static DBOperation getInstance(Context context){
		if (dbOperation==null) {
			dbOperation=new  DBOperation(context);
		}
		return dbOperation;
	}
	public void deleteAllData(){
		db.delete("Course", null, null);
	}
	public void saveCourses(List<Course> list){
		for (Course course : list) {
			ContentValues values=new ContentValues();
			values.put("name", course.getName());
			values.put("day_of_week", course.getDayOfWeek());
			values.put("start_section", course.getStartSection());
			values.put("end_section", course.getEndSection());
			values.put("teacher", course.getTeacher());
			values.put("room", course.getRoom());
			values.put("flag", course.getFlag());
			values.put("start_week", course.getStartWeek());
			values.put("end_week", course.getEndWeek());
			db.insert("Course", null, values);
		}
	}
	public List<Course> getCourses(){
		List<Course> list=new ArrayList<Course>();
		Cursor cursor=db.query("Course", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Course course=new Course();
				course.setName(cursor.getString(cursor.getColumnIndex("name")));
				course.setDayOfWeek(cursor.getString(cursor.getColumnIndex("day_of_week")));
				course.setEndSection(cursor.getInt(cursor.getColumnIndex("end_section")));
				course.setEndWeek(cursor.getInt(cursor.getColumnIndex("end_week")));
				course.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
				course.setRoom(cursor.getString(cursor.getColumnIndex("room")));
				course.setStartSection(cursor.getInt(cursor.getColumnIndex("start_section")));
				course.setStartWeek(cursor.getInt(cursor.getColumnIndex("start_week")));
				course.setTeacher(cursor.getString(cursor.getColumnIndex("teacher")));
				list.add(course);
			} while (cursor.moveToNext());
		}
		return list;
	}
}
