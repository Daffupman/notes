package io.daff.notes.entity.form;

import io.daff.notes.entity.form.base.QueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Data
@ApiModel("笔记查询表单")
public class NoteQueryForm extends QueryForm {

    @ApiModelProperty("笔记名称")
    private String noteName;
    @ApiModelProperty("分类id")
    private Integer categoryId;

    @Override
    public void validate() {
        this.noteName = escapeAndTrimForString(this.noteName);
    }
}
