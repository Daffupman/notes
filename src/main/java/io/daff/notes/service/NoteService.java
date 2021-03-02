package io.daff.notes.service;

import io.daff.notes.entity.Page;
import io.daff.notes.entity.vo.NoteVo;

/**
 * @author daffupman
 * @since 2021/3/1
 */
public interface NoteService {

    Page<NoteVo> queryNotes(String noteName, Integer pageNum, Integer pageSize);
}
