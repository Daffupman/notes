package io.daff.notes.service;

import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.CategoryForm;
import io.daff.notes.entity.form.CategoryQueryForm;
import io.daff.notes.entity.vo.CategoryVo;

/**
 * @author daffupman
 * @since 2021/3/1
 */
public interface CategoryService {

    Page<CategoryVo> query(CategoryQueryForm categoryQueryForm);

    Long saveOrUpdate(CategoryForm noteForm);

    boolean remove(Integer id);
}
