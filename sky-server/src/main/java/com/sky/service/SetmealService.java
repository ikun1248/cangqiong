package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    PageResult findALl(SetmealPageQueryDTO setmealPageQueryDTO);

    void changeStatus(Integer status, Long id);

    void deleteByIds(List<Long> ids);

    void add(SetmealDTO setmealDTO);

    void update(SetmealDTO setmealDTO);

    SetmealVO getByIdWhithDish(Long id);

    List<Setmeal> getBycategoryId(Long categoryId);

    List<DishItemVO> getDishById(Long id);
}
