package com.next.timetable.core.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.next.timetable.core.domain.Lecture;

@Repository
public class LectureMemoryRepository implements LectureRepository {

	private Map<Integer, Lecture> lectures = Collections.unmodifiableMap(new HashMap<Integer, Lecture>());

	public synchronized void save(Lecture lecture) {
		Map<Integer, Lecture> modifiableLectures = new HashMap<Integer, Lecture>(lectures);
		modifiableLectures.put(lecture.getLectureSequence(), lecture);
		this.lectures = Collections.unmodifiableMap(modifiableLectures);
	}

	public Lecture findBySequence(Integer lectureSequence) {
		return lectures.get(lectureSequence);
	}
	
	public List<Lecture> findAll() {
		return Collections.unmodifiableList(new ArrayList<Lecture>(lectures.values()));
	}
}
