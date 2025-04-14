package com.example.tlias.service.impl;

import com.example.tlias.mapper.DeptMapper;
import com.example.tlias.pojo.Dept;
import com.example.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {

        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    //createTime=null, updateTime=null,补全基础属性
    @Override
    public void add(Dept dept) {
        //1.createTime=null, updateTime=null,补全基础属性
        dept.setCreateTime(LocalDateTime.now());//实体类的geter,seter在这里体现作用
        dept.setUpdateTime(LocalDateTime.now());

        //2.调用，mapper接口
        deptMapper.insert(dept);


    }

    @Override
    public Dept getInfo(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //1.补充基础属性
        dept.setUpdateTime(LocalDateTime.now());

        //2.调用mapper接口
        deptMapper.update(dept);
    }


}
