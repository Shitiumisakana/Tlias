package com.example.tlias.service.impl;

import com.example.tlias.mapper.EmpExprMapper;
import com.example.tlias.mapper.EmpMapper;
import com.example.tlias.pojo.*;
import com.example.tlias.service.EmpLogService;
import com.example.tlias.service.EmpService;
import com.example.tlias.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    /**
     * pagehelper分页查询
     *
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }


    /**
     * 新增员工
     */
    @Transactional(rollbackFor = {Exception.class}) //事物管理 -- 不加rollbackfor运行时出现异常runtime exception才会回滚
    @Override
    public void save(Emp emp) {

        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);


            //2.保存工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });

                empExprMapper.insertBatch(exprList);
            }
        } finally {

            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            empLogService.insertLog(empLog);

        }


    }



    /**
     * 批量删除员工
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工基本信息
        empMapper.deleteByIds(ids);


        //2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);

    }

    /**
     * 根据用户id查找用户和工作经历
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工
     *
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1. 根据id修改员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据id删除员工信息
        //2.1 先根据员工ID删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));


        //2.2再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }


    /**
     * 登录
     */
    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper，根据用户名查询用户信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);


        //2.判断是否存在这个员工，存在组装成功登录信息
        if (e != null){
            log.info("用户登录成功，用户信息为：{}", e);
            //生成jwt令牌
            Map<String, Object> Claims = new HashMap<>();
            Claims.put("id", e.getId());
            Claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(Claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        //3.不存在返回null
        return null;
    }


}
