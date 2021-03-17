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
public class CategoryQueryForm extends QueryForm {

    @ApiModelProperty("笔记名称")
    private String cateName;

    @Override
    public void validate() {
        this.cateName = escapeAndTrimForString(this.cateName);
    }
}
