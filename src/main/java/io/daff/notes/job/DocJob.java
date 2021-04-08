package io.daff.notes.job;

import io.daff.notes.service.DocService;
import io.daff.notes.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author daffupman
 * @since 2021/4/2
 */
@Component
@Slf4j
public class DocJob {

    @Resource
    private DocService docService;
    @Resource
    private SnowFlake snowFlake;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void cron() {
        // 设置MDC
        MDC.put("LOG_ID", "LID_" + snowFlake.nextId());

        log.info("定时任务【定时刷新note表数据】：开始执行");
        long startPoint = System.currentTimeMillis();
        docService.refreshDocs();
        log.info("定时任务【定时刷新note表数据】：执行结束。总耗时：{} ms", System.currentTimeMillis() - startPoint);
    }
}
