package com.sky.mapper;
import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {


    Page<Setmeal> findByConditions(Setmeal setmeal);

    @AutoFill(value = OperationType.UPDATE)
    void update(Setmeal setmeal);

    void deleteByIds(List<Long> ids);

    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into setmeal(category_id, name, price, status, description, image,create_time,update_time,create_user,update_user)" +
            "values (#{categoryId},#{name},#{price},#{status},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void add(Setmeal setmeal);

    SetmealVO getByIdWithDish(Long id);

    List<Setmeal> findByIds(List<Long> ids);

    @Select("select * from setmeal where category_id =#{categoryId}")
    List<Setmeal> getBycategoryId(Integer categoryId);
}
