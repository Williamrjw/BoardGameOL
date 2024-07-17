import React, { useState } from 'react';
import axios from 'axios'; 
import { GameTypeEnums } from '../../enums/GameTypeEnums';

/**
 * 创建房间的组件
 * @returns {JSX.Element}
 */
const RoomCreateComponent = () => {
  // 响应式变量管理
  const [roomName, setRoomName] = useState('');
  const [gameType, setGameType] = useState('default');
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleRoomNameChange = (e) => {
    setRoomName(e.target.value);
  };

  const handleGameTypeChange = (e) => {
    setGameType(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null); // 清除之前的错误信息

    try {
      const response = await axios.post('/api/create-room', {
        roomName,
        gameType,
      });
      console.log('Room created successfully:', response.data);
      // 可以在此处处理成功后的逻辑，如跳转到新创建的房间
    } catch (error) {
      setError(error.response ? error.response.data : 'Failed to create room');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div>
      <h2>Create a Room</h2>
      {error && <p>Error: {error}</p>}
      <form onSubmit={handleSubmit}>
        <label>
          Room Name:
          <input type="text" value={roomName} onChange={handleRoomNameChange} required />
        </label>
        <br />
        <label>
          Game Type:
          <select value={gameType} onChange={handleGameTypeChange}>
            {Object.entries(GameTypeEnums).map((gameType) => (
                <option key={gameType.value} value={gameType.value}>{gameType.description}</option>
                ))
            }
          </select>
        </label>
        <br />
        <button type="submit" disabled={isLoading}>
          {isLoading ? 'Creating...' : 'Create Room'}
        </button>
      </form>
    </div>
  );
};

export default RoomCreateComponent;