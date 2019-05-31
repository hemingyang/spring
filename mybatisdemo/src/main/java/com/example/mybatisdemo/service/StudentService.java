package com.example.mybatisdemo.service;

import com.example.mybatisdemo.entity.Student;

import java.util.List;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBysno(String sno);
    Student queryStudentBySno(String sno);

    List<Student> querylist();
}
