package io.daff.notes.service.impl;

import com.github.pagehelper.PageInfo;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.NoteForm;
import io.daff.notes.entity.form.NoteQueryForm;
import io.daff.notes.entity.po.Doc;
import io.daff.notes.entity.po.Note;
import io.daff.notes.entity.vo.DocVo;
import io.daff.notes.entity.vo.NoteVo;
import io.daff.notes.mapper.DocMapper;
import io.daff.notes.mapper.NoteMapper;
import io.daff.notes.service.NoteService;
import io.daff.notes.util.CopyUtil;
import io.daff.notes.util.PageUtil;
import io.daff.notes.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Resource
    private NoteMapper noteMapper;
    @Resource
    private DocMapper docMapper;
    @Resource
    private SnowFlake snowFlake;

    @Override
    public Page<NoteVo> query(NoteQueryForm noteQueryForm) {

        PageUtil.startPage(noteQueryForm, NoteVo.class);
        List<Note> notes = noteMapper.selectLikeByForm(noteQueryForm);

        PageInfo<Note> notePageInfo = new PageInfo<>(notes);
        List<NoteVo> noteVoVos = CopyUtil.copyList(notePageInfo.getList(), NoteVo.class);
        return Page.newPage(notePageInfo.getTotal(), noteVoVos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveOrUpdate(NoteForm noteForm) {

        Note note = CopyUtil.copy(noteForm, Note.class);
        int rows;
        if (noteForm.getId() == null) {
            // save
            note.setId(snowFlake.nextId());
            rows = noteMapper.batchInsert(Collections.singletonList(note));
        } else {
            // update
            rows = noteMapper.batchUpdate(Collections.singletonList(note));
        }
        return rows > 0 ? note.getId() : 0L;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Long id) {
        int rows = noteMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }

    @Override
    public List<DocVo> queryDocsByNoteId(long id) {
        List<Doc> docs = docMapper.selectByNoteId(id);
        return CopyUtil.copyList(docs, DocVo.class);
    }
}
