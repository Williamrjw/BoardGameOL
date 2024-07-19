package com.Williamrjw.game.message.common;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息处理中心
 */
@Component
@Slf4j
public class MessageHandler {

    List<MessageListener> listenerList;

    MessageHandler(List<MessageListener> listeners){
        if(listeners==null){
            log.warn("没有具体的消息实现类");
            listeners = new ArrayList<>();
        }
        listenerList = listeners;
    }

    public void onMessage(String message){
        Message<?> obj = JSON.parseObject(message, Message.class);
        /**
         * 创建消息线程上下文
         * TODO 上下文信息缺少用户信息
         */
        MessageThreadContext.setMessageContext(MessageContext.builder()
                .type(obj.getType())
                .group(obj.getGroup())
                .build()
        );
        try {
            for(MessageListener messageListener : listenerList){
                    if(messageListener.shouldMessage(obj)){
                        messageListener.doMessage(obj.getData());
                    }
            }
        }catch (Exception e){
            log.error("消息处理异常",e);
            // 需要异常退出则抛出异常
            if(obj.exitOnException){
                throw e;
            }
        }finally {
            // 清理消息线程上下文
            MessageThreadContext.clear();
        }
    }
}
