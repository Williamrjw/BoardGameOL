import React, { useState } from 'react';
import WebSocketClient from '../WebSocket/WebSocketClient';

const RoomJoinComponent = ({ onJoinSuccess }) => {
  // 响应式变量管理
  const [roomId, setRoomId] = useState('');
  const [joinError, setJoinError] = useState(null);

  const handleRoomIdChange = (event) => {
    setRoomId(event.target.value);
  };

  const joinRoom = () => {
    if (!roomId) {
      setJoinError('请输入房间ID');
      return;
    }

    // 创建并初始化WebSocketClient组件，传入房间ID
    const wsComponent = (
      <WebSocketClient
        url="http://your-backend-url.com/socket"
        roomId={roomId}
        onMessage={(msg) => {
          // 处理接收到的消息，例如检查是否加入成功
          if (msg.type === 'join-success') {
            onJoinSuccess(roomId);
          } else if (msg.type === 'error') {
            setJoinError(msg.error);
          }
        }}
      />
    );

    // 在这里，你可以将wsComponent添加到DOM中，或者使用状态管理来控制其显示
    // 例如，你可以使用React Context或Redux来管理WebSocket连接状态
  };

  return (
    <div>
      <h2>Join a Room</h2>
      {joinError && <p>{joinError}</p>}
      <input
        type="text"
        placeholder="Enter Room ID"
        value={roomId}
        onChange={handleRoomIdChange}
      />
      <button onClick={joinRoom}>Join Room</button>
    </div>
  );
};

export default RoomJoinComponent;