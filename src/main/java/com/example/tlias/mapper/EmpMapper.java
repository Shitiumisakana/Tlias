package com.example.tlias.mapper;

import com.example.tlias.pojo.Emp;
import com.example.tlias.pojo.EmpQueryParam;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//员工接口
@Mapper
public interface EmpMapper {

//    分页条件查询员工信息
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc ")
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取生成的主键 -- 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) "+
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);


    /**
     * 根据ID批量删除员工基本信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息和工作信息
     */
    Emp getById(Integer id);

    /**
     * 根据id更新员工基本信息
     */
     void updateById(Emp emp);


    /**
     * 统计员工职位人数
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

//    根据用户名密码查询员工信息
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
