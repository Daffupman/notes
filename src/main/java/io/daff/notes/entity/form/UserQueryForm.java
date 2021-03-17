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
@ApiModel("用户查询表单")
public class UserQueryForm extends QueryForm {

    @ApiModelProperty("用户名称")
    private String name;
    @ApiModelProperty("用户登录名")
    private String loginName;

    @Override
    public void validate() {
        this.name = escapeAndTrimForString(this.name);
        this.loginName = escapeAndTrimForString(this.loginName);
    }
}
