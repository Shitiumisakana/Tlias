package com.example.tlias.mapper;

import com.example.tlias.pojo.Clazz;
import com.example.tlias.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 分页查询班级列表
     */

    List<Clazz> list (ClazzQueryParam clazzQueryParam);

    /**
     * 删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加班级
     */

    void add(Clazz clazz);

    /**
     * 根据id查询班级信息
     */

    Clazz getById(Integer id);

    /**
     * 修改班级信息
     */
    @Update("update clazz set name = #{name}, room = #{room}, begin_date = #{beginDate}, end_date = #{endDate}, master_id = #{masterId}, subject = #{subject}, update_time = #{updateTime} where id = #{id}")
    void update(Clazz clazz);

    /**
     * 查询所有班级
     */
    @Select("select * from clazz")
    List<Clazz> findAll();
}
