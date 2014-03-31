package com.next.timetable.core.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.next.timetable.core.domain.Course;

@Repository
public class CourseMemoryRepository implements CourseRepository {

	private Map<Integer, Course> courses = Collections.unmodifiableMap(new HashMap<Integer, Course>());

	public synchronized void save(Course course) {
		Map<Integer, Course> modifiableCourses = new HashMap<Integer, Course>(courses);
		modifiableCourses.put(course.getCourseSequence(), course);
		this.courses = Collections.unmodifiableMap(modifiableCourses);
	}

	public Course findBySequence(Integer courseSequence) {
		return courses.get(courseSequence);
	}

	public List<Course> findAll() {
		return Collections.unmodifiableList(new ArrayList<Course>(courses.values()));
	}
}
