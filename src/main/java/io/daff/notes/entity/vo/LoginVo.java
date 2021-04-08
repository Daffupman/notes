package io.daff.notes.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author daffupman
 * @since 2021/4/8
 */
@Data
public class LoginVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String loginName;
    private String token;
}
