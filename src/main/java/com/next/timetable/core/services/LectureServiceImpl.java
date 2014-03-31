package com.next.timetable.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Lecture;
import com.next.timetable.core.repository.LectureRepository;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	private LectureRepository lectureRepository;

	@Autowired
	private CourseService courseService;

	public List<Lecture> requestAllLectures() {
		List<Lecture> lecturesList = new ArrayList<Lecture>();
		for (Lecture lecture : lectureRepository.findAll()) {
			lecturesList.add(lecture);
		}
		return lecturesList;
	}

	public Lecture getLectureBySequence(Integer lectureSequence) {
		Lecture lecture = lectureRepository.findBySequence(lectureSequence);
		return lecture;
	}

	public List<Course> getCoursesByLecture(Integer lectureSequence) {
		Lecture lecture = lectureRepository.findBySequence(lectureSequence);
		return getCoursesByLecture(lecture);
	}

	public List<Course> getCoursesByLecture(Lecture lecture) {
		List<Course> courseList = new ArrayList<Course>();

		for (Integer courseSequence : lecture.getCourseList()) {
			Course course = courseService.getCourseBySequence(courseSequence);
			courseList.add(course);
		}

		return courseList;
	}
}
