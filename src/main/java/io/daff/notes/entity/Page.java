package io.daff.notes.entity;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页对象模型
 *
 * @author daffupman
 * @since 2021/3/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {

    /**
     * 数据的总数
     */
    private Long total;

    /**
     * 数据列表
     */
    private List<T> list;

    public static <T> Page<T> newPage(Long total, List<T> list) {
        return new Page<>(total, list);
    }

    /**
     * PageInfo转化为Page
     */
    public static <T> Page<T> fromPageInfo(PageInfo<T> pageInfo) {
        return new Page<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
