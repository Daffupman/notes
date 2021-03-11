package io.daff.notes.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.daff.enums.Codes;
import io.daff.exception.BaseException;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.DocForm;
import io.daff.notes.entity.form.DocQueryForm;
import io.daff.notes.entity.po.Doc;
import io.daff.notes.entity.vo.DocVo;
import io.daff.notes.mapper.DocMapper;
import io.daff.notes.service.DocService;
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
public class DocServiceImpl implements DocService {

    @Resource
    private DocMapper docMapper;
    @Resource
    private SnowFlake snowFlake;

    @Override
    public Page<DocVo> pageQuery(DocQueryForm docQueryForm) {

        PageHelper.startPage(docQueryForm.getPageNum(), docQueryForm.getPageSize());
        PageUtil.startPage(docQueryForm, DocVo.class);
        List<Doc> categories = docMapper.selectLikeByForm(docQueryForm);

        PageInfo<Doc> docPageInfo = new PageInfo<>(categories);
        List<DocVo> docVoVos = CopyUtil.copyList(docPageInfo.getList(), DocVo.class);
        return Page.newPage(docPageInfo.getTotal(), docVoVos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveOrUpdate(DocForm docForm) {

        Doc doc = CopyUtil.copy(docForm, Doc.class);
        int rows;
        if (docForm.getId() == null) {
            // save
            doc.setId(snowFlake.nextId());
            rows = docMapper.batchInsert(Collections.singletonList(doc));
        } else {
            // update
            if (doc.getId().equals(doc.getParentId())) {
                throw new BaseException(Codes.BUSINESS_LOGIC_ERROR, "不可将父文档设置成自己");
            }
            rows = docMapper.batchUpdate(Collections.singletonList(doc));
        }
        return rows > 0 ? doc.getId() : 0L;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(List<Long> ids) {
        int rows = docMapper.deleteByIds(ids);
        return rows == ids.size();
    }

    @Override
    public List<Doc> queryAll() {
        return docMapper.selectAll();
    }
}
