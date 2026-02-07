package com.sky.mapper;
import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface DishMapper {


    Page<DishVO> findByCondition(DishPageQueryDTO dishPageQueryDTO);

    @Insert("insert into dish (name, category_id, price,image,description, status, create_time, update_time, create_user, update_user) " +
            "values (#{name}, #{categoryId}, #{price},#{image},#{description}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void add(Dish dish);


    List<Dish> findByIds(List<Long> ids);

    void deleteByIds(List<Long> ids);

    DishVO getByIdWithFlavor(Long id);

    @Select("select * from dish where category_id = #{categoryId}")
    List<DishVO> getByCategoryId(Long categoryId);

    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);
}
