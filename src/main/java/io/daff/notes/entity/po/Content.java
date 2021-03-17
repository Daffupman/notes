package io.daff.notes.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * content表的实体类
 *
 * @author wangzhengjin
 * @since 2021/03/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private Long id;

    private String content;
}