package com.Williamrjw.game.message.common;

import com.Williamrjw.game.common.domain.entity.User;
import lombok.Builder;
import lombok.Data;

/**
 * 消息上下文
 * 这里是给消息处理器的上下文信息
 */
@Data
@Builder
public class MessageContext {

    /**
     * 消息类型
     */
    MessageType type;

    /**
     * 消息分组
     */
    MessageGroup group;


    /**
     * 用户信息
     *
     */
    User user;
}
