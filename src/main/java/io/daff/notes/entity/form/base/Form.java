package io.daff.notes.entity.form.base;

import org.springframework.util.StringUtils;

/**
 * 表单
 *
 * @author daffupman
 * @since 2020/8/21
 */
public interface Form<T> {

    /**
     * 对参数的检查：如果是分页查询，需要检查sort的值是否合法，如果有其他的校验，可以写在这里
     */
    void validate();

    /**
     * 对String类型的字段做trim操作
     */
    default String trim(String field) {
        return StringUtils.isEmpty(field) ? null : field.trim();
    }
}
