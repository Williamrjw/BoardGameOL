package com.Williamrjw.game.message.websocket;

import com.Williamrjw.game.message.common.MessagingProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket实例自动注入
 */
@EnableAutoConfiguration
@Configuration
@EnableConfigurationProperties({MessagingProperties.class,WebSocketProperties.class})
public class WebSocketAutoConfig {
    /**
     * . 注入ServerEndpointExporter， 这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket
     * endpoint
     *
     * @return 注入ServerEndpointExporter
     */
    @Bean
    @ConditionalOnMissingBean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


}
