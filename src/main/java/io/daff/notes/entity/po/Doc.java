package io.daff.notes.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * doc表的实体类
 *
 * @author wangzhengjin
 * @since 2021/03/09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    private Long id;

    private String name;

    private Integer sort;

    private Long parentId;

    private Long noteId;

    private Integer viewCount;

    private Integer voteCount;
}