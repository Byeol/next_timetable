package com.next.timetable.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Lecture {
	private Integer lectureYear;
	private Integer lectureSemester;
	private Integer lectureGrade;
	private final Integer lectureSequence;
	private String subjectNameKo;
	private List<Integer> courseList;

	public Lecture(Integer lectureSequence) {
		this.lectureSequence = lectureSequence;
		this.courseList = new ArrayList<Integer>();
	}

	public void addCourse(Integer courseSequence) {
		courseList.add(courseSequence);
	}

	public void setLecture(Integer lectureYear, Integer lectureSemester, Integer lectureGrade) {
		this.lectureYear = lectureYear;
		this.lectureSemester = lectureSemester;
		this.lectureGrade = lectureGrade;
	}

	public Integer getLectureYear() {
		return lectureYear;
	}

	public Integer getLectureSemester() {
		return lectureSemester;
	}

	public Integer getLectureGrade() {
		return lectureGrade;
	}

	public Integer getLectureSequence() {
		return lectureSequence;
	}

	public String getSubjectNameKo() {
		return subjectNameKo;
	}

	public void setSubjectNameKo(String subjectNameKo) {
		this.subjectNameKo = subjectNameKo;
	}

	public List<Integer> getCourseList() {
		return courseList;
	}
}