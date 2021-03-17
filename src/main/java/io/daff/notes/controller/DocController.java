package io.daff.notes.controller;

import io.daff.entity.Response;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.DocForm;
import io.daff.notes.entity.form.DocQueryForm;
import io.daff.notes.entity.po.Doc;
import io.daff.notes.entity.vo.DocVo;
import io.daff.notes.service.DocService;
import io.daff.notes.util.CopyUtil;
import io.daff.util.StringHelper;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;

    @ApiOperation("文档列表")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping
    public Response<Page<DocVo>> query(@Valid DocQueryForm docQueryForm) {

        docQueryForm.validate();
        Page<DocVo> docsPageInfo = docService.pageQuery(docQueryForm);
        return Response.ok(docsPageInfo);
    }

    @ApiOperation("保存文档")
    @ApiImplicitParams({})
    @ApiResponses({})
    @PutMapping
    public Response<Long> saveOrUpdate(@Valid @RequestBody DocForm docForm) {

        docForm.validate();
        Long docId = docService.saveOrUpdate(docForm);
        return Response.ok(docId);
    }

    @ApiOperation("删除文档")
    @ApiImplicitParams({})
    @ApiResponses({})
    @DeleteMapping("/{ids}")
    public Response<Boolean> remove(@PathVariable String ids) {

        List<Long> idList = StringHelper.parseLongList(ids);
        boolean docId = docService.remove(idList);
        return Response.ok(docId);
    }

    @ApiOperation("文档列表")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping("/all")
    public Response<List<DocVo>> query() {

        List<Doc> docs = docService.queryAll();
        List<DocVo> docVos = CopyUtil.copyList(docs, DocVo.class);
        return Response.ok(docVos);
    }

    @ApiOperation("查询文档内容")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping("/{id}/content")
    public Response<String> queryContent(@PathVariable String id) {

        long docId = StringHelper.parseLong(id);
        return Response.ok(docService.queryContent(docId));
    }
}
