package com.next.timetable.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private final Integer courseSequence;
	private final Integer lectureSequence;
	private Integer placement;
	private String classRoom;
	private String professorNameKo;
	private List<Schedule> scheduleList;

	public Course(Integer courseSequence, Integer lectureSequence) {
		this.courseSequence = courseSequence;
		this.lectureSequence = lectureSequence;
		this.scheduleList = new ArrayList<Schedule>();
	}
	
	public void addSchedule(Schedule schedule) {
		scheduleList.add(schedule);
	}
	
	public void addSchedule(Integer dayWeek, Integer timeStart, Integer timeEnd) {
		Schedule schedule = new Schedule();
		schedule.setDayWeek(dayWeek);
		schedule.setTimeStart(timeStart);
		schedule.setTimeEnd(timeEnd);
		scheduleList.add(schedule);
	}

	public Integer getPlacement() {
		return placement;
	}

	public void setPlacement(Integer placement) {
		this.placement = placement;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getProfessorNameKo() {
		return professorNameKo;
	}

	public void setProfessorNameKo(String professorNameKo) {
		this.professorNameKo = professorNameKo;
	}

	public Integer getCourseSequence() {
		return courseSequence;
	}

	public Integer getLectureSequence() {
		return lectureSequence;
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}
}
