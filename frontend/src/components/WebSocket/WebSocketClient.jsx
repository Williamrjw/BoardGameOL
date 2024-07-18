import React, { useEffect, useState } from 'react';
import io from 'socket.io-client';

/**
 * WebSocket客户端组件
 * @param {string} url - WebSocket服务器的URL
 * @param {string} roomId - 房间的ID
 * @param {function} onMessage - 接收消息的回调函数
 * @returns {JSX.Element}
 */
const WebSocketClient = ({ url, roomId, onMessage }) => {
  // 响应式变量管理
  const [socket, setSocket] = useState(null);

  // 初始化WebSocket连接
  useEffect(() => {
    const newSocket = io(url);
    setSocket(newSocket);

    // 加入房间
    if (roomId) {
      newSocket.emit('join', roomId);
    }

    newSocket.on('connect', () => {
      console.log('Connected to server.');
    });

    newSocket.on('message', (data) => {
      onMessage(data);
      // 调用传进来的回调函数
    });

    // 当组件卸载时断开连接
    return () => {
      newSocket.disconnect();

      // 如果有房间ID，离开房间
      if (roomId) {
        newSocket.emit('leave', roomId);
      }
    };
  }, [url, roomId]);

  const sendMessage = (data) => {
    if (socket) {
      // 发送消息到特定的房间，其中emit是socket.io-client提供的发送消息的方法
      socket.to(roomId).emit('message', data);
    }
  };

  return (
    <div>
      {/* 你可以在这里放置任何与WebSocket相关的UI元素 */}
      {/* 或者将sendMessage方法暴露给父组件使用 */}
    </div>
  );
};

export default WebSocketClient;