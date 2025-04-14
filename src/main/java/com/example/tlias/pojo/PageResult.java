package com.example.tlias.pojo;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//分页结果封装
public class PageResult<T> {
    private Long total;//统计数据
    private List<T> rows;//记录表单内容
}
