package io.daff.notes.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user琛ㄧ殑瀹炰綋绫籠n *
 * @author wangzhengjin
 * @since 2021/03/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String loginName;

    private String name;

    private String password;
}