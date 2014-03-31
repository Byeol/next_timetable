package com.next.timetable.core.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.next.timetable.core.services.ICalService;
import com.next.timetable.util.constants.IConstants;

@Controller
@RequestMapping(IConstants.applicationPath + IConstants.calendarPath)
public class CalendarController {

	@Autowired
	private ICalService iCalService;

	@RequestMapping(value = IConstants.getCalendarActionPath)
	public String getCalendar(
			@RequestParam(value=IConstants.courseNoParam, required=true) String courseNo, Model model) {
		model.addAttribute(IConstants.courseNoParam, courseNo);

		return "getcalendar";
	}

	@RequestMapping(value = IConstants.getCalendarFileActionPath)
	@ResponseBody
	public FileSystemResource getCalendarFile(
			@RequestParam(value=IConstants.courseNoParam, required=true) String courseNo, HttpServletResponse resp) throws ParseException, IOException {
		List<Integer> courseList = new ArrayList<Integer>();

		StringTokenizer st = new StringTokenizer(courseNo, IConstants.courseNoToken);
		while (st.hasMoreTokens()) {
			courseList.add(Integer.parseInt(st.nextToken()));
	     }
		
		File iCalFile = iCalService.getICalFile(courseList, IConstants.iCalFilePath + "/" + UUID.randomUUID().toString());

		resp.setHeader("Content-Disposition", "attachment; filename=" + IConstants.iCalFileName + IConstants.iCalFileExt);
		return new FileSystemResource(iCalFile);
	}
}
