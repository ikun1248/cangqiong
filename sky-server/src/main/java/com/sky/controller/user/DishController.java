package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
public class DishController {

    @Autowired
    DishService dishService;

    @GetMapping("/list")
    public Result list(Long categoryId){

        List<DishVO> dishVOList = dishService.getByCategoryIdWithFlavor(categoryId);

        return Result.success(dishVOList);
    }
}
