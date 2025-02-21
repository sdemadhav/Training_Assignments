package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Teacher;
import com.example.demo.repo.TeacherDao;

@Service
public class TeacherService {

	@Autowired
	TeacherDao dao;	
	
	public String getTeacherByStandard(int standard) {
		if(dao.existsById(standard)) {
			return dao.findById(standard).get().getName().toString();
		}
		return "class teacher does not exist for the specified details !";
	}

	public String addTeacher(Teacher t) {
		if(dao.existsById(t.getStandard())) {
			return "Teacher cant be added as there exists a class teacher already for standard"+t.getStandard() ;
		}
		dao.save(t);
		return "successfully added";
	}
}
