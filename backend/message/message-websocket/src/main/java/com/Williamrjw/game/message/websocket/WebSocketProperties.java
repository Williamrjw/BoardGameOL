package com.Williamrjw.game.message.websocket;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * websocket消息处理配置信息
 */
@Data
@ConfigurationProperties(prefix = "config.messaging.websocket")
public class WebSocketProperties {
    private String url;
    private int connectTimeout;
    private int reconnectAttempts;
    private int reconnectInterval;
}
