package com.next.timetable.core.repository;

import java.util.List;

import com.next.timetable.core.domain.Course;

public interface CourseRepository {

	public void save(Course course);
	public Course findBySequence(Integer courseSequence);
	public List<Course> findAll();
}
