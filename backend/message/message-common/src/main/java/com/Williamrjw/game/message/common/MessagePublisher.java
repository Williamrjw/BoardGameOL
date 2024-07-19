package com.Williamrjw.game.message.common;

/**
 * 消息发布器
 * 由具体由不同消息中间件集成包实现实现
 */
public interface MessagePublisher {
    void publish(Message<?> message);
    void publishTo(Object message);
    void publishGroup(Object message);
    void publishAll(Object message);
}
