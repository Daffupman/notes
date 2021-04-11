package io.daff.notes.job;

import io.daff.notes.service.NoteSnapshotService;
import io.daff.notes.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author daffupman
 * @since 2021/4/11
 */
@Component
@Slf4j
public class NoteSnapshotJob {

    @Resource
    private SnowFlake snowFlake;
    @Resource
    private NoteSnapshotService noteSnapshotService;

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void doSnapshot() {
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        log.info("note表快照生成开始");
        long start = System.currentTimeMillis();
        noteSnapshotService.generateSnapshot();
        log.info("note表快照生成结束，耗时：{} 毫秒", System.currentTimeMillis() - start);
    }
}
