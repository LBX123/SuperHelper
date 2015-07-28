package com.superhelper.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.superhelper.app.model.Course;


public class ParseHtml {
	
	public static List<Course> parseCourse(String html){
		List<Course> list=new ArrayList<Course>();
		Document document = Jsoup.parse(html);
		StringBuilder builder=new StringBuilder();
		Elements elements = document.getElementsByAttributeValue("id", "Table1");
		Element table = elements.get(0).child(0);
		table.child(1).remove();
		table.child(0).child(0).remove();
		table.child(1).child(0).remove();
		table.child(6).child(0).remove();
		table.child(10).child(0).remove();
		for (Element element : table.children()) {
			Elements tr = element.getElementsByTag("tr");
			for (Element e : tr) {
				Elements td = e.getElementsByTag("td");
				for (Element el : td) {
					builder.append(el.text().replaceFirst("^第\\d+节", ""));
				}
				List<Course> result=findCourse(builder.toString());
				for (Course course : result) {
					list.add(course);
				}
				builder.delete(0, builder.length());
//				builder.append("\n");
			}
		}
		return list;
	}
	public static List<Course> findCourse(String str){
		List<Course> list=new ArrayList<Course>();
		Pattern pattern=Pattern.compile("([\u4e00-\u9fa5]+\\S*)\\s?(周[\u4e00-\u9fa5])(第\\S+节)\\{(第\\d+-\\d+周)(\\|[\u4e00-\u9fa5]+)?\\}\\s?([\u4e00-\u9fa5|\\(|\\)|\\/|,]+)\\s?([\u4e00-\u9fa5|\\d|\\(|\\)]+)\\s*");
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			String name=matcher.group(1);
			String dayOfWeek=matcher.group(2);
			String section=matcher.group(3);
			section=section.replaceAll("[\u4e00-\u9fa5]", "");
			String[] sections = section.split(",");
			String startSection=sections[0];
			String endSection="";
			if (sections.length==2) {
				endSection=sections[1];
			}
			String week=matcher.group(4);
			week=week.replaceAll("[\u4e00-\u9fa5]", "");
			String[] weeks = week.split("-");
			String startWeek="";
			String endWeek="";
			if (weeks.length==2) {
				startWeek=weeks[0];
				endWeek=weeks[1];
			}
			String flag=matcher.group(5);
			if (flag==null) {
				flag="";
			}else{
				flag.replaceFirst("\\|", "");
			}
			String teacher=matcher.group(6);
			String room=matcher.group(7);
			list.add(new Course(name, dayOfWeek, IntegerParser.parse(startSection), IntegerParser.parse(endSection),teacher,room, flag, IntegerParser.parse(startWeek), IntegerParser.parse(endWeek)));
		}
		return list;
	}
	
}