package io.daff.notes.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class NoteVo {

    private Long id;
    private String name;
    private Long category1Id;
    private Long category2Id;
    private String description;
    private String cover;
}