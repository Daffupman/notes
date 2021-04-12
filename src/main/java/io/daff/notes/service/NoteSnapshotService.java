package io.daff.notes.service;

import io.daff.notes.entity.vo.StatisticVo;

import java.util.List;

/**
 * @author daffupman
 * @since 2021/3/1
 */
public interface NoteSnapshotService {

    void generateSnapshot();

    List<StatisticVo> queryStatistic();

    List<StatisticVo> queryStatistic30();
}
