package com.example.tlias.service;


import com.example.tlias.pojo.PageResult;
import com.example.tlias.pojo.Student;
import com.example.tlias.pojo.StudentQueryParam;

import java.util.List;


public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    void add(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void handle(Integer id, Integer score);
}
