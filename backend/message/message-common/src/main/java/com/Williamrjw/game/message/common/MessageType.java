package com.Williamrjw.game.message.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 消息类型，用于快速区分消息类型
 * 方便不同游戏逻辑内部进行消息分类
 */
@Getter
public enum MessageType {
    REGISTER("register","注册")
    ;

    @JsonValue
    private final String value;

    private final String desc;

    MessageType(String value, String desc){
        this.value=value;
        this.desc=desc;
    }
}
