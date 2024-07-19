package com.Williamrjw.game.message.websocket;

import com.Williamrjw.game.message.common.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * websocket消息监听器
 */
@Component
@ServerEndpoint(value = "/messaging", configurator = WebSocketServletAwareConfig.class)
@Slf4j
@ConditionalOnExpression("'${config.messaging.type}'.equals('websocket')")
public class WebSocketController {

    /**
     * 消息处理中心实例
     */
    MessageHandler handler;


    @Autowired
    WebSocketController(MessageHandler handler){
        this.handler = handler;
    }

    @OnOpen
    public void onOpen(Session session){
        String id = session.getId();
        WebSocketUtils.sessions.put(id, session);
    }

    @OnMessage
    public void onMessage(Session session,String message){
        /**
         * 收到消息时，通过消息处理中心处理
         */
        handler.onMessage(message);
    }

    @OnClose
    public void onClose(Session session){
        WebSocketUtils.sessions.remove(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        log.error("与【{}】通信异常:",session.getId(),throwable);
    }
}