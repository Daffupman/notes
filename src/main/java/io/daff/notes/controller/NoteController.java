package io.daff.notes.controller;

import io.daff.entity.Response;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.NoteForm;
import io.daff.notes.entity.form.NoteQueryForm;
import io.daff.notes.entity.vo.NoteVo;
import io.daff.notes.service.NoteService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

        Page<NoteVo> notesPageInfo = noteService.queryNotes(noteQueryForm);
        return Response.ok(notesPageInfo);
    }

    @ApiOperation("保存笔记")
    @ApiImplicitParams({})
    @ApiResponses({})
    @PutMapping
    public Response<Long> saveOrUpdate(@RequestBody NoteForm noteForm) {

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
}
