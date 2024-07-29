var MsgFormat = {
    "template_msg": {
        "action": "exchange_card", //动作说明
        "sender_id": "123", //发送者ID
        "receiver_id": "456", //接收者ID，空值表示广播
        "data": {
            //数据
            "description": "This is a test" 
        },
        "validater": (self) => {
            //验证消息是否合法函数
            return true;
        },
        "return": {
            //return不在消息里，后同。返回的消息格式。
        }
    },
    "get_global_view_msg": {
        "action": "get_global_view", 
        "sender_id": "<USER_ID>", 
        "receiver_id": null, 
        "data": {
            "game_id": "card_abc123", 
            "description": "This is a test" 
        },
        "return": {
            "game_id": "game_abc123",
            "players":[
                {
                    "user_id": "user_abc123",
                    "user_name": "User Name",
                    "user_avatar": "http://www.example.com/avatar.jpg",
                    "user_score": 100,
                    "user_hands": []
                },
                {
                    //...
                }
            ]
        }
    },
    "create_game_msg": {
        "action": "create_game",
        "sender_id": "<USER_ID>",
        "receiver_id": "",
        "data": {
            "description": "This is a test"
        },
        "return": {
            "game_id": "game_abc123"
        }
    },
    "join_game_msg": {
        "action": "join_game",
        "sender_id": "<USER_ID>",
        "receiver_id": "",
        "data": {
            "game_id": "game_abc123",
            "description": "This is a test"
        },
        "return": {//return不在消息里，后同
            
        }
    },
    "start_game_msg": {
        "action": "start_game",
        "sender_id": "<USER_ID>",
        "receiver_id": "",
        "data": {
            
        }
    },
    "peek_card_msg": {
        "action": "peek_card",
        "sender_id": "<USER_ID>",
        "receiver_id": "",
        "data": {
            "peekee_id": "game_abc123",
            "peeker_id": "game_abc123",
            "peek_card_index": "0",
            "peek_type": "peek",// init peek / peek / spy
            "description": "This is a test"
        },
        "return": {
            "peeked_card_value": "1"
        }
    },
    "swap_card_msg": {
        "action": "swap_card",
        "sender_id": "<USER_ID>",
        "receiver_id": "",
        "data": {
            "swaper_id": "game_abc123",
            "swapee_id": "game_abc123",
            "swaper_card_index": "0",
            "swapee_card_indexs": ["3","1"],
            "swap_type": "swap",// swap / deck swap / discard swap
            "description": "This is a test"
        }
    },
    
}
