package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;
    @Autowired
    DishFlavorMapper dishFlavorMapper;
    @Autowired
    SetMealDishMapper setMealDishMapper;

    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {

        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.findByCondition(dishPageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getResult());

        return pageResult;
    }

    @Override
    @Transactional
    public void add(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);

        dish.setStatus(1);

        dishMapper.add(dish);


        ArrayList<DishFlavor> dishFlavorList = (ArrayList<DishFlavor>) dishDTO.getFlavors();
        if(dishFlavorList != null && !dishFlavorList.isEmpty()) {
            dishFlavorList.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());
            });
            dishFlavorMapper.add(dishFlavorList);
        }
    }

    @Override
    @Transactional
    public void deleteByIds(List<Long> ids) {

        List<Dish> dishList = dishMapper.findByIds(ids);
        for(Dish dish:dishList){
            if(dish.getStatus()==1){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        List<SetmealDish> setmealDishList = setMealDishMapper.getByDishIds(ids);
        if(setmealDishList!=null && !setmealDishList.isEmpty()){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteByDishIds(ids);


    }

    @Override
    public void changeStatus(Integer status, Long id) {

        Dish dish = Dish.builder()
                .status(status)
                .id(id)
                .build();
        dishMapper.update(dish);
    }

    @Override
    public DishVO getByIdWithFlavor(Long id) {
        return dishMapper.getByIdWithFlavor(id);
    }

    @Override
    public List<DishVO> getByCategoryId(Long categoryId) {

        return dishMapper.getByCategoryId(categoryId);
    }

    @Override
    @Transactional
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);

        dishFlavorMapper.deleteByDishId(dish.getId());
        List<DishFlavor> dishFlavorList = dishDTO.getFlavors();
        if(dishFlavorList != null && !dishFlavorList.isEmpty()) {
            for(DishFlavor dishFlavor:dishFlavorList){
                dishFlavor.setDishId(dish.getId());
            }
            dishFlavorMapper.add(dishFlavorList);
        }

    }
}
