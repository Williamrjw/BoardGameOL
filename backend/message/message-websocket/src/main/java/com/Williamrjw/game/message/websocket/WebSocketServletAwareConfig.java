package com.Williamrjw.game.message.websocket;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 通过springbootContext获取websocket上下文
 * 将websocket的实例也放到springboot容器中
 */
public class WebSocketServletAwareConfig extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        /**
         * TODO 后面加入权限认证后，需要在此添加token信息获取操作
         */
    }

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return ApplicationContextProvider.getContext().getBean(clazz);
    }
}