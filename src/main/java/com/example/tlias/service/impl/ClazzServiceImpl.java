package com.example.tlias.service.impl;

import com.example.tlias.mapper.ClazzMapper;
import com.example.tlias.pojo.*;
import com.example.tlias.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service

public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper ClazzMapper;
    /**
     * 分页查询班级列表
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2.执行查询
        List<Clazz> clazzList = ClazzMapper.list(clazzQueryParam);
        //3.解析查询结果，并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    /**
     * 删除班级
     */
    @Override
    public void delete(Integer id) {
        ClazzMapper.deleteById(id);
    }

    /**
     * 添加班级
     */
    @Override
    public void add(Clazz clazz) {
        //1. 设置更新时间和创建时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2. 调用mapper接口
        ClazzMapper.add(clazz);
    }


    /**
     * 根据id查询班级信息
     */
    @Override
    public Clazz getInfo(Integer id) {
        return  ClazzMapper.getById(id);
    }

    /**
     * 修改班级信息
     */
    @Override
    public void update(Clazz clazz) {
        //1.设置更新时间
        clazz.setUpdateTime(LocalDateTime.now());

        //2.调用mapper接口
        ClazzMapper.update(clazz);
    }

    /**
     * 查询所有班级
     */
    @Override
    public List<Clazz> findAll() {
        return ClazzMapper.findAll();
    }
}
