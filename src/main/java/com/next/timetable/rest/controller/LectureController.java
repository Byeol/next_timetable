package com.next.timetable.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Lecture;
import com.next.timetable.core.services.LectureService;
import com.next.timetable.util.constants.IConstants;

@Controller
@RequestMapping(IConstants.applicationPath + IConstants.lectureRestPath)
public class LectureController {

	@Autowired
	private LectureService lectureService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Lecture> getAllLectures(
			@RequestParam(value=IConstants.lectureYearParam, required=false) Integer year,
			@RequestParam(value=IConstants.lectureSemesterParam, required=false) Integer semester,
			@RequestParam(value=IConstants.lectureGradeParam, required=false) Integer grade) {

		if(year == null && semester == null && grade == null)
			return lectureService.requestAllLectures();

		List<Lecture> lectures = new ArrayList<Lecture>();
		
		for (Lecture lecture : lectureService.requestAllLectures()) {
			if(year != null && (int) year != lecture.getLectureYear()) break;
			if(semester != null && semester != lecture.getLectureSemester()) break;
			if(grade != null && grade != lecture.getLectureGrade()) break;
			lectures.add(lecture);
		}

		return lectures;
	}

	@RequestMapping(value = "/{lectureSequence}")
	@ResponseBody
	public Lecture getLecture(@PathVariable Integer lectureSequence) {
		return lectureService.getLectureBySequence(lectureSequence);
	}

	@RequestMapping(value = "/{lectureSequence}" + IConstants.courseRestPath)
	@ResponseBody
	public List<Course> getCoursesInLecture(@PathVariable Integer lectureSequence) {
		return lectureService.getCoursesByLecture(lectureSequence);
	}
}
