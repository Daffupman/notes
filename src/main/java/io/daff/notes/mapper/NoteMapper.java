package io.daff.notes.mapper;

import io.daff.notes.entity.vo.NoteVo;

import java.util.List;

public interface NoteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteVo record);

    NoteVo selectByPrimaryKey(Long id);

    List<NoteVo> selectAll();

    int updateByPrimaryKey(NoteVo record);

    int batchInsert(List<NoteVo> records);

    int deleteByIds(List<Long> ids);

    List<NoteVo> select(NoteVo record);

    List<NoteVo> selectByIds(List<Long> ids);

    List<NoteVo> selectLikeByNoteName(String noteName);
}