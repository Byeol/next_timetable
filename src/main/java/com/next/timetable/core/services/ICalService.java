package com.next.timetable.core.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import biweekly.ICalendar;
import biweekly.component.VEvent;

public interface ICalService {

	public ICalendar createiCal();
	public void addEvent(List<VEvent> eventList, ICalendar iCal);
	public File makeICalFile(ICalendar iCal, String fileName) throws IOException;
	public File getICalFile(List<Integer> courseList, String fileName) throws IOException, ParseException;
}
