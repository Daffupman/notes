package io.daff.notes.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.CategoryForm;
import io.daff.notes.entity.form.CategoryQueryForm;
import io.daff.notes.entity.po.Category;
import io.daff.notes.entity.vo.CategoryVo;
import io.daff.notes.mapper.CategoryMapper;
import io.daff.notes.service.CategoryService;
import io.daff.notes.util.CopyUtil;
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
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Page<CategoryVo> query(CategoryQueryForm categoryQueryForm) {

        PageHelper.startPage(categoryQueryForm.getPageNum(), categoryQueryForm.getPageSize());
        List<Category> categories = categoryMapper.selectLikeByCateName(categoryQueryForm.getCateName());

        PageInfo<Category> categoryPageInfo = new PageInfo<>(categories);
        List<CategoryVo> categoryVoVos = CopyUtil.copyList(categoryPageInfo.getList(), CategoryVo.class);
        return Page.newPage(categoryPageInfo.getTotal(), categoryVoVos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveOrUpdate(CategoryForm categoryForm) {

        Category category = CopyUtil.copy(categoryForm, Category.class);
        int rows;
        if (categoryForm.getId() == null) {
            // save
            rows = categoryMapper.batchInsert(Collections.singletonList(category));
        } else {
            // update
            rows = categoryMapper.batchUpdate(Collections.singletonList(category));
        }
        return rows > 0 ? category.getId() : 0L;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Integer id) {
        int rows = categoryMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }
}
