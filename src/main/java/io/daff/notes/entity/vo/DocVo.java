package io.daff.notes.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangzhengjin
 * @since 2021/03/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private Long parentId;
    private Integer sort;
}