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
public class CategoryForm implements Form {

    @ApiModelProperty("分类id")
    private Integer id;
    @ApiModelProperty("分类名称")
    private String cateName;
    @ApiModelProperty("父分类id")
    private Integer parentId;

    @Override
    public void validate() {
        this.cateName = trim(this.cateName);
    }
}
