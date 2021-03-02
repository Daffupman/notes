package io.daff.notes.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.vo.NoteVo;
import io.daff.notes.mapper.NoteMapper;
import io.daff.notes.service.NoteService;
import io.daff.notes.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Resource
    private NoteMapper noteMapper;

    @Override
    public Page<NoteVo> queryNotes(String noteName, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<NoteVo> notes = noteMapper.selectLikeByNoteName(noteName);

        PageInfo<NoteVo> notePageInfo = new PageInfo<>(notes);
        List<NoteVo> noteVoVos = CopyUtil.copyList(notePageInfo.getList(), NoteVo.class);
        return Page.newPage(notePageInfo.getTotal(), noteVoVos);
    }
}
