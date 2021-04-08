package io.daff.notes.service;

import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.DocForm;
import io.daff.notes.entity.form.DocQueryForm;
import io.daff.notes.entity.po.Doc;
import io.daff.notes.entity.vo.DocVo;

import java.util.List;

/**
 * @author daffupman
 * @since 2021/3/1
 */
public interface DocService {

    Page<DocVo> pageQuery(DocQueryForm docQueryForm);

    Long saveOrUpdate(DocForm docForm);

    boolean remove(List<Long> ids);

    List<Doc> queryAll();

    String queryContent(Long id);

    boolean voteDoc(long docId);

    void refreshDocs();
}
