package io.daff.notes.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * note_snapshot琛ㄧ殑瀹炰綋绫籠n *
 * @author wangzhengjin
 * @since 2021/04/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteSnapshot {
    private Long id;

    private Long noteId;

    private Date createTime;

    private Integer viewCount;

    private Integer voteCount;

    private Integer viewIncrease;

    private Integer voteIncrease;
}