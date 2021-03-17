package io.daff.notes.entity.form;

import io.daff.notes.entity.form.base.Form;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author daffupman
 * @since 2021/3/17
 */
@Data
@ApiModel("密码重置表单")
public class PasswordResetForm implements Form {

    @ApiModelProperty("用户id")
    @NotNull(message = "【用户id】不能为空")
    private Long id;

    @ApiModelProperty("新密码")
    @NotBlank(message = "【新密码】不能为空")
    @Length(max = 255, message = "【新密码】长度不能超过255位字符")
    private String password;

    @Override
    public void validate() {
    }
}
