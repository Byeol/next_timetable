package com.next.timetable.core.services;

import java.text.ParseException;
import java.util.List;

import biweekly.component.VEvent;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Lecture;
import com.next.timetable.core.domain.Schedule;

public interface EventService {

	public List<VEvent> makeEvent(Integer courseSequence) throws ParseException;
	public List<VEvent> makeEvent(Course course) throws ParseException;
	public VEvent makeEvent(Lecture lecture, Course course, Schedule schedule) throws ParseException;
}
