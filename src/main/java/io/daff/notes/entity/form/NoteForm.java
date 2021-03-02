package io.daff.notes.entity.form;

import io.daff.notes.entity.form.base.Form;
import io.daff.notes.entity.vo.NoteVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Data
@ApiModel("笔记表单")
public class NoteForm implements Form<NoteVo> {

    @ApiModelProperty("笔记名称")
    private String noteName;

    @Override
    public void validate() {
        this.noteName = trim(this.noteName);
    }

    @Override
    public NoteVo convertToPo() {
        return NoteVo.builder().name(this.noteName).build();
    }
}
