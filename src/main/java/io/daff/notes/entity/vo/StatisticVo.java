package io.daff.notes.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author daffupman
 * @since 2021/4/11
 */
@Data
public class StatisticVo {

    @JsonFormat(pattern="MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 总阅读数
     */
    private Integer viewCount;

    /**
     * 总点赞数
     */
    private Integer voteCount;

    /**
     * 今日阅读增长
     */
    private Integer viewIncrease;

    /**
     * 今日点赞增长
     */
    private Integer voteIncrease;
}
