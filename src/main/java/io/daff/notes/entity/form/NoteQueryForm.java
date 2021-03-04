package io.daff.notes.entity.form;

import io.daff.notes.entity.form.base.QueryForm;
import io.daff.notes.entity.vo.NoteVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Data
@ApiModel("笔记查询表单")
public class NoteQueryForm extends QueryForm<NoteVo> {

    @ApiModelProperty("笔记名称")
    private String noteName;

    @Override
    public void validate() {
        this.noteName = escapeAndTrimForString(this.noteName);
    }
}
