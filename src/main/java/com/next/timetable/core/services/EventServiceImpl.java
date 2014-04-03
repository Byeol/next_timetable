package com.next.timetable.core.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biweekly.component.VEvent;
import biweekly.util.Recurrence;
import biweekly.util.Recurrence.DayOfWeek;
import biweekly.util.Recurrence.Frequency;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Lecture;
import com.next.timetable.core.domain.Schedule;
import com.next.timetable.util.constants.IConstants;
import com.next.timetable.util.messages.MessageService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private CourseService courseService;

	@Autowired
	private LectureService lectureService;

	@Autowired
	private MessageService messageService;

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public List<VEvent> makeEvent(Integer courseSequence) throws ParseException {
		if(courseService.getCourseBySequence(courseSequence) == null) // NullPointerException
			return new ArrayList<VEvent>();
		
		return makeEvent(courseService.getCourseBySequence(courseSequence));
	}

	public List<VEvent> makeEvent(Course course) throws ParseException {
		List<VEvent> eventList = new ArrayList<VEvent>();
		List<Schedule> scheduleList = course.getScheduleList();

		if(lectureService.getLectureBySequence(course.getLectureSequence()) == null) // NullPointerException
			return eventList;
		
		Lecture lecture = lectureService.getLectureBySequence(course.getLectureSequence());
		for (Schedule schedule : scheduleList) {
			VEvent event = makeEvent(lecture, course, schedule);
			eventList.add(event);
		}

		return eventList;
	}

	public VEvent makeEvent(Lecture lecture, Course course, Schedule schedule) throws ParseException {
		VEvent event = new VEvent();

		event.setSummary(lecture.getSubjectNameKo());
		event.setDescription(course.getProfessorNameKo() + " " + messageService.getMessage(IConstants.professorTitleMessage));
		event.setLocation(course.getClassRoom());

		String startDate=String.valueOf(IConstants.eventStartYear) + "-" + String.valueOf(IConstants.eventStartMonth) + "-" + (IConstants.eventStartDay + schedule.getDayWeek() - 1);
		String startTime=(IConstants.eventStartTime + schedule.getTimeStart() - 1) + ":" + 0;
		String endTime=(IConstants.eventStartTime + schedule.getTimeEnd()) + ":" + 0;
		event.setDateStart(df.parse(startDate + " " + startTime));
		event.setDateEnd(df.parse(startDate + " " + endTime));

		String endDate=String.valueOf(IConstants.eventEndYear) + "-" + String.valueOf(IConstants.eventEndMonth) + "-" + (IConstants.eventEndDay) + " " + IConstants.eventEndTime + ":" + 0;
		Recurrence.Builder recur = new Recurrence.Builder(Frequency.WEEKLY);
		recur.byDay(getDayOfWeek(schedule.getDayWeek()));
		recur.until(df.parse(endDate));
		event.setRecurrenceRule(recur.build());

		return event;
	}
	
	private DayOfWeek getDayOfWeek (Integer dayweek) {
		DayOfWeek dayOfWeek = null;
		switch(dayweek) {
		case 1: dayOfWeek = Recurrence.DayOfWeek.MONDAY; break;
		case 2: dayOfWeek = Recurrence.DayOfWeek.TUESDAY; break;
		case 3: dayOfWeek = Recurrence.DayOfWeek.WEDNESDAY; break;
		case 4: dayOfWeek = Recurrence.DayOfWeek.THURSDAY; break;
		case 5: dayOfWeek = Recurrence.DayOfWeek.FRIDAY; break;
		case 6: dayOfWeek = Recurrence.DayOfWeek.SATURDAY; break;
		case 7: dayOfWeek = Recurrence.DayOfWeek.SUNDAY; break;
		}
		return dayOfWeek;
	}
}
