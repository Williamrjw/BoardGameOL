package com.Williamrjw.game.message.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 消息中间件的实现类型
 */
@Data
@ConfigurationProperties(prefix = "config.messaging")
public class MessagingProperties {
    private String type;
}
