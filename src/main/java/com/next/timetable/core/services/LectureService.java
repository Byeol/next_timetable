package com.next.timetable.core.services;

import java.util.List;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Lecture;

public interface LectureService {

	public List<Lecture> requestAllLectures();
	public Lecture getLectureBySequence(Integer lectureSequence);
	public List<Course> getCoursesByLecture(Integer lectureSequence);
	public List<Course> getCoursesByLecture(Lecture lecture);
}
