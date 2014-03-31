package com.next.timetable.core.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Schedule;

public interface CourseService {

	public List<Course> requestAllCourses();
	public Course getCourseBySequence(Integer courseSequence);
	public List<Schedule> getScheduleByCourse(Integer courseSequence);
	public List<Schedule> getScheduleByCourse(Course course);
	public URL getDetailURL(Course course) throws MalformedURLException;
}
