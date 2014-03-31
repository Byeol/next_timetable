package com.next.timetable.core.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.timetable.ProductInfo;
import com.next.timetable.util.constants.IConstants;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

@Service
public class ICalServiceImpl implements ICalService {

	@Autowired
	private EventService eventService;

	public ICalendar createiCal() {
		ICalendar iCal = new ICalendar();
		iCal.setProductId("-//" + ProductInfo.getDeveloperName() + "//" + ProductInfo.getProductFullName() + "//" + IConstants.iCalLanguageCode);

		return iCal;
	}

	public void addEvent(List<VEvent> eventList, ICalendar iCal) {
		for (VEvent event : eventList) {
			iCal.addEvent(event);
		}
	}

	public File makeICalFile(ICalendar iCal, String fileName) throws IOException {
		File file = new File(fileName);
		Biweekly.write(iCal).go(file);

		return file;
	}

	public File getICalFile(List<Integer> courseList, String fileName) throws IOException, ParseException {
		ICalendar iCal = createiCal();

		for (Integer course : courseList) {
			addEvent(eventService.makeEvent(course), iCal);
		}
		
		File iCalFile = makeICalFile(iCal, fileName + IConstants.iCalFileExt);
		return iCalFile;
	}
}
