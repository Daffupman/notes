package io.daff.notes.entity.form;

import io.daff.notes.entity.form.base.Form;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Data
@ApiModel("文档表单")
public class DocForm implements Form {

    @ApiModelProperty("文档id")
    private Long id;
    @ApiModelProperty("文档名称")
    @NotBlank(message = "文档名称不能为空")
    private String name;
    @ApiModelProperty("文档名称")
    @NotNull(message = "顺序不能为空")
    private Integer sort;
    @ApiModelProperty("父文档id")
    @NotNull(message = "父文档id不能为空")
    private Long parentId;
    @ApiModelProperty("笔记id")
    @NotNull(message = "笔记id不能为空")
    private Long noteId;
    @NotBlank(message = "文档内容不能为空")
    private String content;

    @Override
    public void validate() {
        this.name = trim(this.name);
    }
}
