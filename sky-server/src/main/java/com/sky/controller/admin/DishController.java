package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    DishService dishService;
    @GetMapping("/page")
    public Result page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询:{}",dishPageQueryDTO);
        PageResult pageResult = dishService.page(dishPageQueryDTO);

        return Result.success(pageResult);

    }

    @PostMapping
    public Result add(@RequestBody DishDTO dishDTO){
        log.info("新增菜品:{}",dishDTO);
        dishService.add(dishDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteByIds(@RequestParam List<Long> ids){
        log.info("批量删除菜品:{}",ids);
        dishService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result changeStatus(@PathVariable Integer status,Long id){
        log.info("修改菜品状态:{},{}",status,id);
        dishService.changeStatus(status,id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        log.info("查询菜品id为{}信息",id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @GetMapping("/list")
    public Result getByCategoryId(Long categoryId){
        log.info("查询分类id为{}的菜品",categoryId);
        List<DishVO> list = dishService.getByCategoryId(categoryId);
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){

        log.info("修改菜品{}",dishDTO);
        dishService.update(dishDTO);
        return Result.success();
    }

}
