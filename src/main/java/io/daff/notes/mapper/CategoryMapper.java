package io.daff.notes.mapper;


import io.daff.notes.entity.po.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);

    int batchInsert(List<Category> records);

    int deleteByIds(List<Integer> ids);

    List<Category> select(Category record);

    List<Category> selectByIds(List<Integer> ids);

    int batchUpdate(List<Category> records);

    List<Category> selectLikeByCateName(String cateName);
}