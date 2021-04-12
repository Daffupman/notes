package io.daff.notes.service.impl;

import io.daff.notes.entity.vo.StatisticVo;
import io.daff.notes.mapper.NoteSnapshotMapper;
import io.daff.notes.service.NoteSnapshotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author daffupman
 * @since 2021/4/11
 */
@Service
public class NoteSnapshotServiceImpl implements NoteSnapshotService {

    @Resource
    private NoteSnapshotMapper noteSnapshotMapper;

    @Override
    public void generateSnapshot() {
        noteSnapshotMapper.generate();
    }

    @Override
    public List<StatisticVo> queryStatistic() {
        return noteSnapshotMapper.statistics();
    }

    @Override
    public List<StatisticVo> queryStatistic30() {
        return noteSnapshotMapper.statistics30();
    }
}
