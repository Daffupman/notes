package io.daff.notes.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * category表的实体类
 *
 * @author wangzhengjin
 * @since 2021/03/04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;

    private String cateName;

    private Integer parentId;
}