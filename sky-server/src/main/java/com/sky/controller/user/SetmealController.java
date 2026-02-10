package com.sky.controller.user;

import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @RequestMapping("/list")
    public Result list(Long categoryId){
        log.info("查询分类id为{}的套餐",categoryId);

        List<Setmeal> setmealList = setmealService.getBycategoryId(categoryId);


        return Result.success(setmealList);
    }

    @RequestMapping("dish/{id}")
    public Result getByIdWhithDish(@PathVariable Long id){
        log.info("查询套餐id为{}的菜品信息",id);
        List<DishItemVO> dishList = setmealService.getDishById(id);

        return Result.success(dishList);
    }
}
