package com.example.tlias.service;


import com.example.tlias.pojo.Dept;

import java.util.List;

public interface DeptService {

    //查询所有部门数据
    List<Dept> findAll();


    //根据id删除部门
    void deleteById(Integer id);

    //创建新部门
    void add(Dept dept);

    Dept getInfo(Integer id);

    void update(Dept dept);
}
