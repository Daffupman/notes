package io.daff.notes.service;

import io.daff.notes.ws.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author daffupman
 * @since 2021/4/11
 */
@Service
public class WsService {

    @Resource
    private WebSocketServer ws;

    @Async
    public void sendInfo(String message, String logId) {
        MDC.put("LOG_ID", logId);
        // 通过websocket推送消息
        ws.sendInfo("文章：【" + message + "】被点赞了！");
    }
}
