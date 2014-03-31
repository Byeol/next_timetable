package com.next.timetable.util.constants;

public interface IConstants {

	final static String applicationPath = "/";
	final static String calendarPath = "/calendar";
	final static String testPath = "/test";
	final static String aboutPath = "/about";
	final static String getCalendarActionPath = "/getcalendar.do";
	final static String getCalendarFileActionPath = "/getical.do";
	final static String getMakeTimetablePath = "/maketimetable.do";

	final static String lectureRestPath = "/lectures";
	final static String courseRestPath = "/courses";
	final static String scheduleRestPath = "/schedules";

	final static String courseNoParam = "courseNo";
	final static String courseNoToken = ",";

	final static String lectureYearParam = "year";
	final static String lectureSemesterParam = "semester";
	final static String lectureGradeParam = "grade";

	final static Integer eventStartYear = 2014;
	final static Integer eventStartMonth = 3;
	final static Integer eventStartDay = 3;
	final static Integer eventStartTime = 8;

	final static String iCalLanguageCode = "KO";
	final static String iCalFilePath = "ical";
	final static String iCalFileName = "next";
	final static String iCalFileExt = ".ics";

	final static String courseInfoURL = "http://academy.nhnnext.org/courseDetail/courseInfo.nhn?courseSequence=";

	final static String lectureDataCommonXmlFile = "./lectureData/lectures_0.xml";
	final static String lectureDataGrade1XmlFile = "./lectureData/lectures_1.xml";
	final static String lectureDataGrade2XmlFile = "./lectureData/lectures_2.xml";

	final static String lectureYearXmlExpression = "/lectures/@year";
	final static String lectureSemesterXmlExpression = "/lectures/@semester";
	final static String lectureGradeXmlExpression = "/lectures/@grade";
	final static String lectureXmlExpression = "/lectures/lecture";
	final static String lectureSequenceXmlExpression = "./@sequence";
	final static String subjectNameKoXmlExpression = "./subjectName[@lang='ko']";
	final static String courseXmlExpression = "./courses/course";
	final static String courseSequenceXmlExpression = "./@sequence";
	final static String placementXmlExpression = "./placement";
	final static String classRoomXmlExpression = "./classRoom";
	final static String professorNameKoXmlExpression = "./professorName[@lang='ko']";
	final static String scheduleXmlExpression = "./schedules/schedule";
	final static String dayWeekXmlExpression = "dayWeek";
	final static String timeStartXmlExpression = "timeStart";
	final static String timeEndXmlExpression = "timeEnd";
	
	final static String professorTitleMessage = "professorTitle";
}
