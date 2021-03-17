package io.daff.notes.mapper;

import io.daff.notes.entity.form.DocQueryForm;
import io.daff.notes.entity.po.Doc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Doc record);

    Doc selectByPrimaryKey(Long id);

    List<Doc> selectAll();

    int updateByPrimaryKey(Doc record);

    int batchInsert(List<Doc> records);

    int deleteByIds(List<Long> ids);

    List<Doc> select(Doc record);

    List<Doc> selectByIds(List<Long> ids);

    int batchUpdate(List<Doc> records);

    List<Doc> selectLikeByForm(DocQueryForm docQueryForm);

    List<Doc> selectByNoteId(@Param("noteId") String id);
}