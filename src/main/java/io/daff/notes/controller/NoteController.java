package io.daff.notes.controller;

import io.daff.entity.Response;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.NoteForm;
import io.daff.notes.entity.form.NoteQueryForm;
import io.daff.notes.entity.vo.DocVo;
import io.daff.notes.entity.vo.NoteVo;
import io.daff.notes.service.NoteService;
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
@RequestMapping("/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    @ApiOperation("笔记列表")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping
    public Response<Page<NoteVo>> query(@Valid NoteQueryForm noteQueryForm) {

        noteQueryForm.validate();
        Page<NoteVo> notesPageInfo = noteService.query(noteQueryForm);
        return Response.ok(notesPageInfo);
    }

    @ApiOperation("保存笔记")
    @ApiImplicitParams({})
    @ApiResponses({})
    @PutMapping
    public Response<Long> saveOrUpdate(@RequestBody @Valid NoteForm noteForm) {

        noteForm.validate();
        Long noteId = noteService.saveOrUpdate(noteForm);
        return Response.ok(noteId);
    }

    @ApiOperation("删除笔记")
    @ApiImplicitParams({})
    @ApiResponses({})
    @DeleteMapping("/{id}")
    public Response<Boolean> remove(@PathVariable Long id) {

        boolean noteId = noteService.remove(id);
        return Response.ok(noteId);
    }

    @ApiOperation("查询指定笔记下的所有文档列表")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping("/{id}/docs")
    public Response<List<DocVo>> queryDocsByNoteId(@PathVariable String id) {

        List<DocVo> docVos = noteService.queryDocsByNoteId(StringHelper.parseLong(id));
        return Response.ok(docVos);
    }
}
