package com.example.tlias.service.impl;

import com.example.tlias.mapper.EmpMapper;
import com.example.tlias.mapper.StudentMapper;
import com.example.tlias.pojo.ClassCountData;
import com.example.tlias.pojo.JobOption;
import com.example.tlias.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     *  统计员工职位人数
     */
    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();//map:pos=教研主管， num=1

        //2.组装结果，并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();//把mapper传递过来的pos数据流封装到jobList里（Java的序列化）
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    /**
     *  统计员工性别人数
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }


    /**
     * 统计学员学历
     */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }


    /**
     * 统计班级人数
     */
    @Override
    public ClassCountData getStudentCountData() {
        List<Map<String, Object>> studentCountList = studentMapper.countStudentCountData();
        List<String> clazzList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        for (Map<String, Object> map : studentCountList) {
            // 获取 clazzName，假设它不会为 null
            clazzList.add((String) map.get("clazzName"));

            // 获取 count，并处理可能的 null 值
            Object countObj = map.get("count");
            int count = (countObj != null && countObj instanceof Number) ? ((Number) countObj).intValue() : 0;
            dataList.add(count);
        }
        return new ClassCountData(clazzList, dataList);
    }




}
