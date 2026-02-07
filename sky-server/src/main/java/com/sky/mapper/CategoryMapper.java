package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(name,sort,type,status,create_time,update_time,create_user,update_user) " +
            "values(#{name},#{sort},#{type},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void add(Category category);


    Page<Category> page(CategoryPageQueryDTO categoryPageQueryDTO);


    void update(Category category);

    @Delete("Delete from category where id =#{id}")
    void deleteById(Long id);

    @Select("select * from category where type =#{type}")
    List<Category> listByType(Integer type);
}
