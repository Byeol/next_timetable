package com.next.timetable.core.domain;

public class Schedule {
	private Integer dayWeek;
	private Integer timeStart;
	private Integer timeEnd;

	public Schedule() {
	}

	public Schedule(Integer dayWeek, Integer timeStart, Integer timeEnd) {
		this.setDayWeek(dayWeek);
		this.setTimeStart(timeStart);
		this.setTimeEnd(timeEnd);
	}

	public Integer getDayWeek() {
		return dayWeek;
	}

	public void setDayWeek(Integer dayWeek) {
		this.dayWeek = dayWeek;
	}

	public Integer getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Integer timeStart) {
		this.timeStart = timeStart;
	}

	public Integer getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Integer timeEnd) {
		this.timeEnd = timeEnd;
	}
}
