package io.daff.notes.mapper;

import io.daff.notes.entity.po.NoteSnapshot;

import java.util.List;

public interface NoteSnapshotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteSnapshot record);

    NoteSnapshot selectByPrimaryKey(Long id);

    List<NoteSnapshot> selectAll();

    int updateByPrimaryKey(NoteSnapshot record);

    int batchInsert(List<NoteSnapshot> records);

    int deleteByIds(List<Long> ids);

    List<NoteSnapshot> select(NoteSnapshot record);

    List<NoteSnapshot> selectByIds(List<Long> ids);

    int batchUpdate(List<NoteSnapshot> records);

    void generate();
}