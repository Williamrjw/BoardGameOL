import json


class Message:
    def __init__(self, action, data, sender, receiver=None):
        self.action = action
        self.sender = sender
        self.data = data    
        self.receiver = receiver
    def to_json(self):
        return json.dumps(self.__dict__)
    
    def __str__(self):
        return self.to_json()
    
    # 解析json
    @staticmethod
    def from_json(json_str):
        if json_str is None:
            return Message("acquire_new_command", None, -1)
        try:
            msg_dict = json.loads(json_str)
        except json.JSONDecodeError:
            return Message("acquire_new_command", None, -1)
                
        required_keys = ['action', 'data', 'sender_id','receiver_id']
        for key in required_keys:
            if key not in msg_dict:
                return Message("acquire_new_command", None, -1)
        
        # 将msg_dict转换为Message对象
        msg = Message(msg_dict['action'], msg_dict['data'], msg_dict['sender_id'], msg_dict.get('receiver'))
        return msg


def msg_handler(msg):
    
    rtn_msg = {}
    
    if msg.action == "get_global_view":
        rtn_msg = { "game_id": "game_abc123",
            "players": [
                {
                    "user_id": "user_abc123",
                    "user_name": "User Name",
                    "user_avatar": "http://www.example.com/avatar.jpg",
                    "user_score": 100,
                    "user_hands": []
                },
            ]}
    elif msg.action == "peek_card":
        rtn_msg = { 
            "peeked_card_value": "1"
        }
    elif msg.action == "swap_card":
        rtn_msg = {
            
        }
    else: rtn_msg = msg.__str__()
    return rtn_msg

if __name__ == "__main__":
    msg = '{"action": "get_global_view"}'
    print(msg_handler(msg))
    msg = ""
    print(msg_handler(msg))
    msg = None
    print(msg_handler(msg))
    
    
