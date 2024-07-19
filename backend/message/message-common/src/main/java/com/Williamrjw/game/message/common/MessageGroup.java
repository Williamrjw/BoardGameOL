package com.Williamrjw.game.message.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 消息分组
 * 用于区分不同游戏
 */
@Getter
public enum MessageGroup {
    POKE("poker","扑克纸牌游戏");

    @JsonValue
    private final String value;

    private final String desc;

    MessageGroup(String value, String desc){
        this.value = value;
        this.desc = desc;
    }
}
