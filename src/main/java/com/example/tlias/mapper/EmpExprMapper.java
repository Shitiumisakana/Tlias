package com.example.tlias.mapper;

import com.example.tlias.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//员工工作经历接口
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历信息
     */

    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工的id批量删除工作经历
     */
    void deleteByEmpIds(List<Integer> empIds);
}
