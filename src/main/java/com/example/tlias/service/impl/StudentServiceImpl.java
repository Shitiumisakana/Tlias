package com.example.tlias.service.impl;

import com.example.tlias.mapper.StudentMapper;
import com.example.tlias.pojo.Clazz;
import com.example.tlias.pojo.PageResult;
import com.example.tlias.pojo.Student;
import com.example.tlias.pojo.StudentQueryParam;
import com.example.tlias.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查找学生
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //2.执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        //3.解析查询结果，并封装
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    /**
     * 删除学生
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    /**
     * 添加学生
     */
    @Override
    public void add(Student student) {
        //1.获取创建时间和更新时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        //2.调用mapper接口
        studentMapper.add(student);
    }

    /**
     * 根据id查询学生
     */
    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getInfo(id);
    }

    /**
     * 修改学生
     */
    @Override
    public void update(Student student) {
        //1.设置更新时间
        student.setUpdateTime(LocalDateTime.now());
        //2.调用mapper接口
        studentMapper.update(student);
    }

    /**
     * 违纪处理
     */
    @Override
    public void handle(Integer id, Integer score) {
        //1.根据id查询学生
        Student student = studentMapper.getInfo(id);
        //2.修改违纪次数和违纪分值
        student.setViolationCount((short) (student.getViolationCount() + 1));
        student.setViolationScore((short) (student.getViolationScore() + score));
        //3.设置更新时间
        student.setUpdateTime(LocalDateTime.now());
        //4.调用mapper接口
        studentMapper.updateByScore(student);
    }


}
