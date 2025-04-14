package com.example.tlias.mapper;

import com.example.tlias.pojo.Student;
import com.example.tlias.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 分页查找学生
     */

    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 删除学生
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 添加学生
     */
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "values (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime})")
    void add(Student student);

    /**
     * 根据id查询学生
     */
    Student getInfo(Integer id);

    /**
     * 修改学生
     */
    @Update("update student set name = #{name}, no = #{no}, gender = #{gender}, phone = #{phone}, id_card = #{idCard}, is_college = #{isCollege}, address = #{address}, degree = #{degree}, graduation_date = #{graduationDate}, clazz_id = #{clazzId}, update_time = #{updateTime} where id = #{id}")
    void update(Student student);

    /**
     * 违纪处理
     */
    @Update("update student set violation_count = #{violationCount}, violation_score = #{violationScore}, update_time = #{updateTime} where id = #{id}")
    void updateByScore(Student student);

    /**
     * 统计学员学历
     */
    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();

    /**
     * 统计班级人数
     */
    @MapKey("clazzName")
    List<Map<String, Object>> countStudentCountData();
}
