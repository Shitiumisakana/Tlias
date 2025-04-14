package com.example.tlias.controller;

import com.example.tlias.pojo.*;
import com.example.tlias.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController

public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 分页查找学生
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询，参数：{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 批量删除学生
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除，id集合：{}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 添加学生
     */
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学生：{}", student);
        studentService.add(student);
        return Result.success();
    }

    /**
     * 根据id查询学生
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询学生:{}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 修改学生
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生：{}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result handle(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪处理，id：{},score:{}", id, score);
        studentService.handle(id, score);
        return Result.success();
    }
}
