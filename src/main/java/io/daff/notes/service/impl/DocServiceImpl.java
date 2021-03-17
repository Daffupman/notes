package io.daff.notes.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.daff.enums.Codes;
import io.daff.exception.BaseException;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.DocForm;
import io.daff.notes.entity.form.DocQueryForm;
import io.daff.notes.entity.po.Content;
import io.daff.notes.entity.po.Doc;
import io.daff.notes.entity.vo.DocVo;
import io.daff.notes.mapper.ContentMapper;
import io.daff.notes.mapper.DocMapper;
import io.daff.notes.service.DocService;
import io.daff.notes.util.CopyUtil;
import io.daff.notes.util.PageUtil;
import io.daff.notes.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DocServiceImpl implements DocService {

    @Resource
    private DocMapper docMapper;
    @Resource
    private ContentMapper contentMapper;
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
        Content content = CopyUtil.copy(docForm, Content.class);
        int rows;
        if (docForm.getId() == null) {
            // save
            long docId = snowFlake.nextId();
            doc.setId(docId);
            rows = docMapper.batchInsert(Collections.singletonList(doc));
            if (rows > 0) {
                content.setId(docId);
                rows = contentMapper.batchInsert(Collections.singletonList(content));
            }
        } else {
            // update
            if (doc.getId().equals(doc.getParentId())) {
                throw new BaseException(Codes.BUSINESS_LOGIC_ERROR, "不可将父文档设置成自己");
            }
            rows = docMapper.batchUpdate(Collections.singletonList(doc));
            if (rows > 0) {
                rows = contentMapper.batchUpdate(Collections.singletonList(content));
                if (rows <= 0) {
                    contentMapper.batchInsert(Collections.singletonList(content));
                }
            }
        }
        return rows > 0 ? doc.getId() : 0L;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(List<Long> ids) {
        int rows = docMapper.deleteByIds(ids);
        if (rows > 0) {
            rows = contentMapper.deleteByIds(ids);
        }
        return rows == ids.size();
    }

    @Override
    public List<Doc> queryAll() {
        return docMapper.selectAll();
    }

    @Override
    public String queryContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        return content == null ? null : content.getContent();
    }
}
