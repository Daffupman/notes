package io.daff.notes.mapper;

import io.daff.notes.entity.po.Content;

import java.util.List;

public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    Content selectByPrimaryKey(Long id);

    List<Content> selectAll();

    int updateByPrimaryKey(Content record);

    int batchInsert(List<Content> records);

    int deleteByIds(List<Long> ids);

    List<Content> select(Content record);

    List<Content> selectByIds(List<Long> ids);

    int batchUpdate(List<Content> records);
}