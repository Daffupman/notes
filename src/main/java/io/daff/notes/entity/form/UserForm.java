package io.daff.notes.entity.form;

import io.daff.notes.entity.form.base.Form;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Data
@ApiModel("用户表单")
public class UserForm implements Form {

    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名")
    @NotNull(message = "【用户名】不能为空")
    private String name;
    @ApiModelProperty("用户登录名")
    @NotNull(message = "【用户登录名】不能为空")
    private String loginName;
    @ApiModelProperty("密码")
    @NotNull(message = "【密码】不能为空")
    @Length(max = 255, message = "【密码】不能超过255位字符")
    // @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$", message = "【密码】至少包含 数字和英文，长度6-30")
    private String password;

    @Override
    public void validate() {
        this.name = trim(this.name);
        this.loginName = trim(this.loginName);
    }
}
