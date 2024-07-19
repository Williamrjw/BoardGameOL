package com.Williamrjw.game.message.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 客户端传递的消息
 * @param <T>
 */
@Data
public class Message<T extends Serializable> implements Serializable {
    /**
     * 消息类型
     */
    MessageType type;

    /**
     * 消息分组
     */
    MessageGroup group;

    /**
     * 消息异常是否退出
     */
    boolean exitOnException = true;

    /**
     * 消息主体
     */
    T data;

    public Message(MessageType type) {
        this.type = type;
    }

    public Message(MessageType type, T data){
        this.type = type;
        this.data = data;
    }
}
