package io.daff.notes.controller;

import io.daff.consts.SystemConstants;
import io.daff.entity.Response;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.vo.NoteVo;
import io.daff.notes.service.NoteService;
import io.daff.util.StringHelper;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    @ApiOperation("用户列表")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping
    public Response<Page<NoteVo>> queryNotes(@RequestParam(value = "noteName",required = false) String noteName,
                                             @RequestParam(value = SystemConstants.PAGE_NUM, defaultValue = "1") String pageNum,
                                             @RequestParam(value = SystemConstants.PAGE_SIZE, defaultValue = "10") String pageSize) {

        Page<NoteVo> notesPageInfo = noteService.queryNotes(
                noteName,
                StringHelper.parseInt(pageNum),
                StringHelper.parseInt(pageSize)
        );
        return Response.ok(notesPageInfo);
    }
}
