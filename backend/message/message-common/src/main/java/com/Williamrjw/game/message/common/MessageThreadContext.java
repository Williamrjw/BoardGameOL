package com.Williamrjw.game.message.common;

/**
 * 消息处理线程上下文
 * 为{@link MessageContext}的线程包装
 */
public class MessageThreadContext {
    private static final ThreadLocal<MessageContext> threadLocal = new ThreadLocal<>();

    public static void setMessageContext(MessageContext context) {
        threadLocal.set(context);
    }

    public static MessageContext getMessageContext() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
