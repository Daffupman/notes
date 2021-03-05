package io.daff.notes.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * note表的实体类
 *
 * @author wangzhengjin
 * @since 2021/03/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Serializable {
    private Long id;

    private String name;

    private Integer category1Id;

    private Integer category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;

    private static final long serialVersionUID = 1L;
}