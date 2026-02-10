package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("admin/setmeal")
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @GetMapping("/page")
    public Result findAll(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询:{}",setmealPageQueryDTO);

        PageResult pageResult = setmealService.findALl(setmealPageQueryDTO);

        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    public Result changeStatus(@PathVariable Integer status, Long id){
        log.info("修改菜品状态:{},{}",status,id);
        setmealService.changeStatus(status,id);
        return Result.success();
    }

    @DeleteMapping()
    public Result deleteByIds(@RequestParam List<Long> ids){

        log.info("批量删除菜品:{}",ids);
        setmealService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping()
    public Result add(@RequestBody SetmealDTO setmealDTO){
        log.info("新增菜品:{}",setmealDTO);
        setmealService.add(setmealDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        log.info("查询菜品id为{}信息",id);
        SetmealVO setmealVO = setmealService.getByIdWhithDish(id);
        return Result.success(setmealVO);
    }

    @PutMapping
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改菜品{}",setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }
}
