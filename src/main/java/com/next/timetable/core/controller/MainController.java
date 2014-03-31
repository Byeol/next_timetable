package com.next.timetable.core.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import com.next.timetable.core.services.DataService;
import com.next.timetable.util.constants.IConstants;

@Controller
@RequestMapping(IConstants.applicationPath)
public class MainController {

	@Autowired
	private DataService dataService;

	@RequestMapping
	public String getMain() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		if(!dataService.isLectureDataCreated())
		{
			File lectureDataCommonXmlFile = new File(IConstants.lectureDataCommonXmlFile);
			File lectureDataGrade1XmlFile = new File(IConstants.lectureDataGrade1XmlFile);
			File lectureDataGrade2XmlFile = new File(IConstants.lectureDataGrade2XmlFile);
			dataService.makeLectureData(lectureDataCommonXmlFile);
			dataService.makeLectureData(lectureDataGrade1XmlFile);
			//dataService.makeLectureData(lectureDataGrade2XmlFile);
		}

		return "home";
	}

	@RequestMapping(value = IConstants.getMakeTimetablePath)
	public String getMakeTimetable() {
		return "maketimetable";
	}

	@RequestMapping(value = IConstants.aboutPath)
	public String getAbout() {
		return "about";
	}
}
