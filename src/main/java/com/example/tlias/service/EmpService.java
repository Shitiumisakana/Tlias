package com.example.tlias.service;

import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.EmpQueryParam;
import com.example.tlias.pojo.LoginInfo;
import com.example.tlias.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {
    /**
     * 实现分页的方法
     *
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
