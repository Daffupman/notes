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
public abstract class QueryForm implements Form {

    @ApiModelProperty("页码")
    @DecimalMin(value = "1", message = "页码最小为1，请重新输入")
    @DecimalMax(value = "1000", message = "页码最大为1000，请重新输入")
    private Integer pageNum = 1;

    @ApiModelProperty("每页条数")
    @DecimalMin(value = "1", message = "每页条数最小为1，请重新输入")
    @DecimalMax(value = "1000", message = "每页条数最大为1000，请重新输入")
    private Integer pageSize = 1000;

    /**
     * 对String类型的字段做escape和trim操作
     */
    public String escapeAndTrimForString(String field) {

        return StringUtils.isEmpty(field) ? null :
                SqlLikeEscapeUtils.escapeSpecialChar(field.trim());
    }
}
