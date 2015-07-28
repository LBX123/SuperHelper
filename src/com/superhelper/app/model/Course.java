package com.superhelper.app.model;

public class Course {
	private String name;//课程名
	private String dayOfWeek;//周几
	private int startSection;//第几节开始
	private int endSection;//第几节结束
	private String teacher;//老师
	private String room;//教室
	private String flag;//单双周或都要
	private int startWeek;//开始周0
	private int endWeek;//结束周
	public Course(){}
	public Course(String name, String dayOfWeek, int startSection,
			int endSection, String teacher, String room, String flag,
			int startWeek, int endWeek) {
		super();
		this.name = name;
		this.dayOfWeek = dayOfWeek;
		this.startSection = startSection;
		this.endSection = endSection;
		this.teacher = teacher;
		this.room = room;
		this.flag = flag;
		this.startWeek = startWeek;
		this.endWeek = endWeek;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getStartSection() {
		return startSection;
	}
	public void setStartSection(int startSection) {
		this.startSection = startSection;
	}
	public int getEndSection() {
		return endSection;
	}
	public void setEndSection(int endSection) {
		this.endSection = endSection;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getStartWeek() {
		return startWeek;
	}
	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}
	public int getEndWeek() {
		return endWeek;
	}
	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}
	public void printAll(){
		System.out.println(name+"");
		System.out.println(dayOfWeek+"");
		System.out.println(startSection+"");
		System.out.println(endSection+"");
		System.out.println(teacher+"");
		System.out.println(room+"");
		System.out.println(startWeek+"");
		System.out.println(endWeek+"");
		System.out.println(flag+"");
	}
}
