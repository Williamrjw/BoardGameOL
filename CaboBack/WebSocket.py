# !/usr/bin/env python3
# -*- coding: utf-8 -*-
from MsgHandler import msg_handler,Message

import asyncio
import websockets



clients = []
async def cabo_ws_server(websocket, path):
    # 连接建立时，可以在这里添加逻辑，比如通知其他客户端新玩家加入
    print(f"新连接建立: {websocket.remote_address}")
    clients.append(websocket)
    
    try:
        async for message in websocket:
            # 处理接收到的消息，这里仅做示例，实际应根据游戏逻辑处理
            print(f"收到消息: {message}")
            
            
            # 广播消息给所有连接的客户端
            await broadcast(str(msg_handler(Message.from_json(message))))
    finally:
        # 连接关闭时的清理工作
        print(f"连接关闭: {websocket.remote_address}")

async def broadcast(message):
    # 假设clients是一个全局变量，存储所有连接的客户端
    for client in clients:
        await client.send(message)



start_server = websockets.serve(cabo_ws_server, "localhost", 8765)

asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()
