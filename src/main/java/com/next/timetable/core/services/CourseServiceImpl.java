package com.next.timetable.core.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Schedule;
import com.next.timetable.core.repository.CourseRepository;
import com.next.timetable.util.constants.IConstants;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> requestAllCourses() {
		List<Course> coursesList = new ArrayList<Course>();
		for (Course course : courseRepository.findAll()) {
			coursesList.add(course);
		}
		return coursesList;
	}

	public Course getCourseBySequence(Integer courseSequence) {
		Course course = courseRepository.findBySequence(courseSequence);
		return course;
	}

	public List<Schedule> getScheduleByCourse(Integer courseSequence) {
		Course course = getCourseBySequence(courseSequence);
		return getScheduleByCourse(course);
	}

	public List<Schedule> getScheduleByCourse(Course course) {
		return course.getScheduleList();
	}

	public URL getDetailURL(Course course) throws MalformedURLException
	{
		Integer courseSequence = course.getCourseSequence();
		URL detailURL = new URL(IConstants.courseInfoURL + courseSequence);
		return detailURL;
	}
}
