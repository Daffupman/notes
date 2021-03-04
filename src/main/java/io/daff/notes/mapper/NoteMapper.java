package io.daff.notes.mapper;

import io.daff.notes.entity.po.Note;
import io.daff.notes.entity.vo.NoteVo;

import java.util.List;

public interface NoteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Note record);

    NoteVo selectByPrimaryKey(Long id);

    List<NoteVo> selectAll();

    int updateByPrimaryKey(Note record);

    int batchInsert(List<Note> records);

    int deleteByIds(List<Long> ids);

    List<Note> select(Note record);

    List<Note> selectByIds(List<Long> ids);

    int batchUpdate(List<Note> records);

    List<Note> selectLikeByNoteName(String noteName);
}