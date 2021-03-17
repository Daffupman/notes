package io.daff.notes.entity.form;

import io.daff.notes.entity.form.base.Form;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daffupman
 * @since 2021/3/17
 */
@Data
@ApiModel("登录表单")
public class LoginForm implements Form {

    @ApiModelProperty("登录名")
    private String loginName;
    @ApiModelProperty("密码")
    private String password;

    @Override
    public void validate() {
    }
}
