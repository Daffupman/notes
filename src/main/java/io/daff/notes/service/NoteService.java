package io.daff.notes.service;

import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.NoteForm;
import io.daff.notes.entity.form.NoteQueryForm;
import io.daff.notes.entity.vo.NoteVo;

/**
 * @author daffupman
 * @since 2021/3/1
 */
public interface NoteService {

    Page<NoteVo> query(NoteQueryForm noteQueryForm);

    Long saveOrUpdate(NoteForm noteForm);

    boolean remove(Long id);
}
