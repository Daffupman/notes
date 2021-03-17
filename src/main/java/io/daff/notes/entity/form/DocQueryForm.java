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
@ApiModel("文档查询表单")
public class DocQueryForm extends QueryForm {

    @ApiModelProperty("文档名称")
    private String docName;

    @Override
    public void validate() {
        this.docName = escapeAndTrimForString(this.docName);
    }
}
