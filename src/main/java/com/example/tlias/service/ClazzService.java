package com.example.tlias.service;

import com.example.tlias.pojo.Clazz;
import com.example.tlias.pojo.ClazzQueryParam;
import com.example.tlias.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void delete(Integer id);

    void add(Clazz clazz);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    List<Clazz> findAll();
}
