package com.next.timetable.core.services;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public interface DataService {

	public void makeLectureData(File lectureXmlFile) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException;
	public boolean isLectureDataCreated();
}
