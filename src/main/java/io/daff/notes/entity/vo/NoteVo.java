package io.daff.notes.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private Integer category1Id;
    private Integer category2Id;
    private String description;
    private String cover;
    private Integer docCount;
    private Integer viewCount;
    private Integer voteCount;
}