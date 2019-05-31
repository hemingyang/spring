package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.entity.Student;

public interface StudentMapper {

    int add(Student student);
    int update(Student student);
    int deleteByIds(String sno);
    Student queryStudentById(Long id);
}
