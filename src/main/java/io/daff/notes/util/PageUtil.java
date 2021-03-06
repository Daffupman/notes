package io.daff.notes.util;

import com.github.pagehelper.PageHelper;
import io.daff.enums.Codes;
import io.daff.enums.Messages;
import io.daff.exception.BaseException;
import io.daff.exception.ParamValidateException;
import io.daff.notes.entity.form.base.QueryForm;
import io.daff.notes.entity.form.base.SortedQueryForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * pageHelper工具类
 *
 * @author daffupman
 * @since 2020/8/22
 */
@Slf4j
public class PageUtil {

    /**
     * 对QueryForm启用分页工具
     */
    public static void startPage(QueryForm<?> queryForm, @NotNull Class<?> clazz) {

        Integer pageNum = queryForm.getPageNum();
        Integer pageSize = queryForm.getPageSize();

        if (pageNum == null && pageSize == null) {
            return;
        }

        if (pageNum == null || pageSize == null) {
            throw new ParamValidateException("页码与页大小需要同时填写，请补充填写");
        }

        if (queryForm instanceof SortedQueryForm) {

            SortedQueryForm<?> sortedQueryForm = (SortedQueryForm<?>) queryForm;
            String sort = sortedQueryForm.getSort();
            String ordinal = sortedQueryForm.getOrdinal();
            if (!StringUtils.isEmpty(sort) && StringUtils.isEmpty(ordinal) ||
                    StringUtils.isEmpty(sort) && !StringUtils.isEmpty(ordinal)) {
                throw new ParamValidateException("排序字段与排序规则需要同时填写，请补充填写");
            }

            if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(ordinal)) {
                // 检查sort字段的类型是否是String类型
                boolean isString = false;
                try {
                    Class<?> clz = Class.forName(clazz.getName());
                    for (Field declaredField : clz.getDeclaredFields()) {
                        if (sort.equalsIgnoreCase(declaredField.getName())) {
                            if (declaredField.getType().equals(String.class)) {
                                isString = true;
                            }
                            break;
                        }
                    }
                } catch (ClassNotFoundException e) {
                    log.error("传入的class对象不存在", e);
                    throw new BaseException(Codes.SYSTEM_ERROR, Messages.SYSTEM_ERROR);
                }

                String orderByClause = isString ?
                        "CONVERT(" + sort + " USING GBK)" + " " + ordinal : sort + " " + ordinal;
                PageHelper.startPage(pageNum, pageSize, orderByClause);
                return;
            }
        }

        PageHelper.startPage(pageNum, pageSize);

    }
}
