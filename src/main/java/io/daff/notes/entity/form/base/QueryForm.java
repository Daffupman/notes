package io.daff.notes.entity.form.base;

import io.daff.util.SqlLikeEscapeUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * @author daffupman
 * @since 2020/8/17
 */
@Data
public abstract class QueryForm<T> implements Form<T> {

    @ApiModelProperty("页码")
    @DecimalMin(value = "1", message = "页码最小为1，请重新输入")
    @DecimalMax(value = "999", message = "页码最大为999，请重新输入")
    private Integer pageNum = 1;

    @ApiModelProperty("页大小")
    @DecimalMin(value = "1", message = "页大小最小为1，请重新输入")
    @DecimalMax(value = "999", message = "页大小最大为999，请重新输入")
    private Integer pageSize = 999;

    /**
     * 对String类型的字段做escape和trim操作
     */
    public String escapeAndTrimForString(String field) {

        return StringUtils.isEmpty(field) ? null :
                SqlLikeEscapeUtils.escapeSpecialChar(field.trim());
    }
}
