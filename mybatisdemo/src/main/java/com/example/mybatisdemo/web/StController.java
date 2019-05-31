package com.example.mybatisdemo.web;

import com.example.mybatisdemo.entity.Student;
import com.example.mybatisdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StController {


    @Autowired
    private StudentService studentService;

    @RequestMapping( value = "/querystudent", method = RequestMethod.GET)
    public Student queryStudentBySno(String sno) {
        return this.studentService.queryStudentBySno(sno);
    }



    @RequestMapping(value = "/querylist")
    public List <Student >querylist(){

        List<Student>  list =  studentService.querylist();
        return  list;
    }
}
