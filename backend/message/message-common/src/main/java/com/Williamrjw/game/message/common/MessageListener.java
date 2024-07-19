package com.Williamrjw.game.message.common;

import org.springframework.core.Ordered;

/**
 * 消息处理器，不同游戏自己写一个listener
 */
public interface MessageListener extends Ordered {

    /**
     * 是否需要处理消息
     * @param message 消息完整信息
     * @return 是|否
     */
    boolean shouldMessage(Message<?> message);

    /**
     * 消息处理逻辑
     * 在这里时，消息线程上下文已经构建，可以通过 {@link MessageThreadContext} 获取
     * @param data
     */
    void doMessage(Object data);
}
