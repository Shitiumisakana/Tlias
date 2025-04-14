package com.example.tlias.controller;

import com.example.tlias.pojo.ClassCountData;
import com.example.tlias.pojo.JobOption;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// 所有报表的控制
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    /**
     *  统计员工职位人数,看接口文档，data后是{}说明返回的是是一个对象，所以要定义实体类包装
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     *  统计员工性别人数,看接口文档，data后是【】说明返回的是是一个集合，所以可以不定义实体类直接用list就可以了，定义实体类也可以实现但是过于繁琐
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计学员学历
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("统计学员学历");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

    /**
     * 统计班级人数
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("统计班级人数");
        ClassCountData classCountData = reportService.getStudentCountData();
        return Result.success(classCountData);
    }

}
