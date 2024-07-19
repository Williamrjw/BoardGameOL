package com.Williamrjw.game.message.websocket;


import com.Williamrjw.game.message.common.Message;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * websocket消息实例存储工具
 * 将websocket消息实例与websocket监听器分离实现更方便的消息发送
 */
@Slf4j
public class WebSocketUtils {
    private WebSocketUtils(){}
    public static final Map<String, Session> sessions = new HashMap<>();

    private static final Object GLOBAL_LOCK = new Object();

    public static boolean send(Session session, Message<?> message) {
        if (session == null || !session.isOpen()) {
            return false;
        }

        synchronized (GLOBAL_LOCK){
            log.info("发送socket【{}】消息",message.getType().getDesc());
            final RemoteEndpoint.Basic basic = session.getBasicRemote();
            if (basic == null) {
                return false;
            }
            try {
                basic.sendText(JSON.toJSONString(message));
                return true;
            } catch (Exception e) {
                log.error("发送socket消息失败: " ,e);
                try {
                    session.close();
                } catch (IOException ioException) {
                    log.error("socket连接实例关闭失败:",ioException);
                }
                return false;
            }
        }
    }
}

