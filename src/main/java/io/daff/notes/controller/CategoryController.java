package io.daff.notes.controller;

import io.daff.entity.Response;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.CategoryForm;
import io.daff.notes.entity.form.CategoryQueryForm;
import io.daff.notes.entity.po.Category;
import io.daff.notes.entity.vo.CategoryVo;
import io.daff.notes.service.CategoryService;
import io.daff.notes.util.CopyUtil;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation("分类列表")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping
    public Response<Page<CategoryVo>> pageQuery(@Valid CategoryQueryForm categoryQueryForm) {

        Page<CategoryVo> notesPageInfo = categoryService.pageQuery(categoryQueryForm);
        return Response.ok(notesPageInfo);
    }

    @ApiOperation("分类列表")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping("/all")
    public Response<List<CategoryVo>> query() {

        List<Category> categories = categoryService.queryAll();
        List<CategoryVo> categoryVos = CopyUtil.copyList(categories, CategoryVo.class);
        return Response.ok(categoryVos);
    }

    @ApiOperation("保存分类")
    @ApiImplicitParams({})
    @ApiResponses({})
    @PutMapping
    public Response<Long> saveOrUpdate(@RequestBody CategoryForm noteForm) {

        noteForm.validate();
        Long noteId = categoryService.saveOrUpdate(noteForm);
        return Response.ok(noteId);
    }

    @ApiOperation("删除分类")
    @ApiImplicitParams({})
    @ApiResponses({})
    @DeleteMapping("/{id}")
    public Response<Boolean> remove(@PathVariable Integer id) {

        boolean noteId = categoryService.remove(id);
        return Response.ok(noteId);
    }
}
