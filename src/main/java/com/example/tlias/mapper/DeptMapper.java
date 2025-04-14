package com.example.tlias.mapper;

import com.example.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface DeptMapper {

    //查询全部用户
    @Select("select id, name, create_time, update_time from dept order by update_time desc")//查询所有部门信息并根据更新时间倒序
    List<Dept> findAll();


    //根据id删除用户
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept(name, create_time, update_time) VALUES (#{name}, #{createTime}, #{updateTime})")//数据库属性名用下划线，字段名用驼峰可以自动识别
    void insert(Dept dept);

    //根据id查询部门
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
