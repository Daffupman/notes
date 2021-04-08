package io.daff.notes.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author daffupman
 * @since 2021/4/2
 */
// @Component
@Slf4j
public class JobTest {

    /**
     * 按固定频率跑批
     */
    @Scheduled(fixedRate = 5000)
    public void simple() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss:SSS");
        String dataString = simpleDateFormat.format(new Date());
        log.info("每隔5秒执行一次：{}", dataString);
    }

    /**
     * 自定义cron表达式跑批。执行定时器任务的线程只有一个，如果到点了，上一个任务没有跑完，那么到点的这个任务就不会执行
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void cron() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss:SSS");
        String dataString = simpleDateFormat.format(new Date());
        log.info("每隔2秒执行一次：{}", dataString);
    }
}
