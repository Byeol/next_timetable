package com.next.timetable.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Schedule;
import com.next.timetable.core.services.CourseService;
import com.next.timetable.util.constants.IConstants;

@Controller
@RequestMapping(IConstants.applicationPath + IConstants.courseRestPath)
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Course> getAllCourses() {
		return courseService.requestAllCourses();
	}

	@RequestMapping(value = "/{courseSequence}")
	@ResponseBody
	public Course getCourse(@PathVariable Integer courseSequence) {
		return courseService.getCourseBySequence(courseSequence);
	}

	@RequestMapping(value = "/{courseSequence}" + IConstants.scheduleRestPath)
	@ResponseBody
	public List<Schedule> getScheduleByCourse(@PathVariable Integer courseSequence) {
		return courseService.getScheduleByCourse(courseSequence);
	}
}
