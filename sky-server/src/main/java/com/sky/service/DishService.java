package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    void add(DishDTO dishDTO);

    void deleteByIds(List<Long> ids);

    void changeStatus(Integer status, Long id);

    DishVO getByIdWithFlavor(Long id);

    List<DishVO> getByCategoryId(Long categoryId);

    void update(DishDTO dishDTO);

    List<DishVO> getByCategoryIdWithFlavor(Long categoryId);
}
