package com.next.timetable.core.services;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.next.timetable.core.domain.Course;
import com.next.timetable.core.domain.Lecture;
import com.next.timetable.core.domain.Schedule;
import com.next.timetable.core.repository.CourseRepository;
import com.next.timetable.core.repository.LectureRepository;
import com.next.timetable.util.constants.IConstants;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private LectureRepository lectureRepository;

	private boolean isLectureDataCreated = false;

	private XPath xPath = XPathFactory.newInstance().newXPath();

	public void makeLectureData(File lectureXmlFile) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(lectureXmlFile);

		Integer lectureYear = Integer.parseInt((String) xPath.evaluate(IConstants.lectureYearXmlExpression, document, XPathConstants.STRING));
		Integer lectureSemester = Integer.parseInt((String) xPath.evaluate(IConstants.lectureSemesterXmlExpression, document, XPathConstants.STRING));
		Integer lectureGrade = Integer.parseInt((String) xPath.evaluate(IConstants.lectureGradeXmlExpression, document, XPathConstants.STRING));

		NodeList lectures = getLectures(document);
		for(int index=0; index<lectures.getLength(); index++) {
			Lecture lecture = makeLecture(lectures.item(index));
			lecture.setLecture(lectureYear, lectureSemester, lectureGrade);
			lectureRepository.save(lecture);
		}

		isLectureDataCreated = true;
	}

	public NodeList getLectures(Object document) throws XPathExpressionException {
		return (NodeList) xPath.evaluate(IConstants.lectureXmlExpression, document, XPathConstants.NODESET);
	}

	public Lecture makeLecture(Node lectureNode) throws XPathExpressionException {
		Integer lectureSequence = Integer.parseInt((String) xPath.evaluate(IConstants.lectureSequenceXmlExpression, lectureNode, XPathConstants.STRING));
		String subjectNameKo = (String) xPath.evaluate(IConstants.subjectNameKoXmlExpression, lectureNode, XPathConstants.STRING);

		Lecture lecture = new Lecture(lectureSequence);
		lecture.setSubjectNameKo(subjectNameKo);

		NodeList courses = getCourses(lectureNode);
		for(int index=0; index<courses.getLength(); index++) {
			Course course = makeCourse(courses.item(index), lectureSequence);
			courseRepository.save(course);
			lecture.addCourse(course.getCourseSequence());
		}

		return lecture;
	}

	public NodeList getCourses(Node lecture) throws XPathExpressionException {
		return (NodeList) xPath.evaluate(IConstants.courseXmlExpression, lecture, XPathConstants.NODESET);
	}
	
	public Course makeCourse(Node courseNode, Integer lectureSequence) throws NumberFormatException, XPathExpressionException {
		Integer courseSequence = Integer.parseInt((String) xPath.evaluate(IConstants.courseSequenceXmlExpression, courseNode, XPathConstants.STRING));
		Integer placement = Integer.parseInt((String) xPath.evaluate(IConstants.placementXmlExpression, courseNode, XPathConstants.STRING));
		String classRoom = (String) xPath.evaluate(IConstants.classRoomXmlExpression, courseNode, XPathConstants.STRING);
		String professorNameKo = (String) xPath.evaluate(IConstants.professorNameKoXmlExpression, courseNode, XPathConstants.STRING);

		Course course = new Course(courseSequence, lectureSequence);
		course.setPlacement(placement);
		course.setClassRoom(classRoom);
		course.setProfessorNameKo(professorNameKo);

		NodeList schedules = getSchedules(courseNode);
		for(int index=0; index<schedules.getLength(); index++) {
			Schedule schedule = makeSchedule(schedules.item(index));
			course.addSchedule(schedule);
		}

		return course;
	}
	
	public NodeList getSchedules(Node course) throws XPathExpressionException {
		return (NodeList) xPath.evaluate(IConstants.scheduleXmlExpression, course, XPathConstants.NODESET);
	}
	
	public Schedule makeSchedule(Node scheduleNode) throws NumberFormatException, XPathExpressionException {
		Integer dayWeek = Integer.parseInt((String) xPath.evaluate(IConstants.dayWeekXmlExpression, scheduleNode, XPathConstants.STRING));
		Integer timeStart = Integer.parseInt((String) xPath.evaluate(IConstants.timeStartXmlExpression, scheduleNode, XPathConstants.STRING));
		Integer timeEnd = Integer.parseInt((String) xPath.evaluate(IConstants.timeEndXmlExpression, scheduleNode, XPathConstants.STRING));

		Schedule schedule = new Schedule(dayWeek, timeStart, timeEnd);
		return schedule;
	}

	public boolean isLectureDataCreated() {
		return isLectureDataCreated;
	}
}
