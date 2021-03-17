package io.daff.notes.entity.form;

import io.daff.notes.entity.form.base.Form;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Data
@ApiModel("笔记表单")
public class NoteForm implements Form {

    @ApiModelProperty("笔记id")
    private Long id;
    @ApiModelProperty("笔记名称")
    private String name;
    @ApiModelProperty("笔记描述")
    private String description;
    @ApiModelProperty("分类一")
    private Integer category1Id;
    @ApiModelProperty("分类二")
    private Integer category2Id;
    @ApiModelProperty("笔记封面")
    private String cover;

    @Override
    public void validate() {
        this.name = trim(this.name);
        this.description = trim(this.description);
        this.cover = trim(this.cover);
    }
}
