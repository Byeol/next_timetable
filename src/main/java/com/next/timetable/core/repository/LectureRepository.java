package com.next.timetable.core.repository;

import java.util.List;

import com.next.timetable.core.domain.Lecture;

public interface LectureRepository {

	public void save(Lecture lecture);
	public Lecture findBySequence(Integer lectureSequence);
	public List<Lecture> findAll();
}
