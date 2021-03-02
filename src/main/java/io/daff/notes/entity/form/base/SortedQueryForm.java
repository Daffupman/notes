package io.daff.notes.entity.form.base;

import io.daff.anno.Limit;
import io.daff.exception.ParamValidateException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author daffupman
 * @since 2020/8/17
 */
@Data
public abstract class SortedQueryForm<T> extends QueryForm<T> {

    @ApiModelProperty("排序字段")
    private String sort;

    @ApiModelProperty("排序规则")
    @Limit(value = {"asc", "desc"}, message = "排序规则不合法，请重新输入")
    private String ordinal;

    /**
     * 检查排序字段是否合法，需要根据业务需要传入合法的排序字段
     */
    protected void handleSort(String... allowFields) {
        if (!StringUtils.isEmpty(this.sort) && allowFields.length > 0
                && !Arrays.asList(allowFields).contains(this.sort)) {
            throw new ParamValidateException("排序字段不合法，请重新输入");
        }
    }
}
