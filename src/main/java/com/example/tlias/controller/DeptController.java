package com.example.tlias.controller;

import com.example.tlias.pojo.Dept;
import com.example.tlias.pojo.Result;
import com.example.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
//@controller加@responseBody,@responseBody作用为把方法的返回值转化为数据直接响应给前端，如果是对象或集合则转为json再响应
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts", method = RequestMethod.GET)//method指定请求方式
    @GetMapping//简化
    public Result list(){
        log.info("查询全部部门数据");
//        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);//打印在后台

    }

    //根据id删除部门
    @DeleteMapping
    public Result delete(Integer id){
//        System.out.println("删除的部门id为：" + id);
        log.info("删除的部门id为：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){//用requestbody封装从前台接受到的json并封装到dept里
//        System.out.println("添加部门" + dept);
        log.info("添加部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }


    //根据id查询
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){//获取路径参数
//        System.out.println("查询的部门信息为" + id);
        log.info("查询的部门信息为：{}", id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping

    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门：" + dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
