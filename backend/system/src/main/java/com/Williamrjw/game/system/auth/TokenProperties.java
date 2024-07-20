package com.Williamrjw.game.system.auth;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "config.token")
public class TokenProperties {
    String key;
    // 令牌秘钥
    private String secret;

    // 令牌有效期（默认30分钟）
    private int expireTime;
}
