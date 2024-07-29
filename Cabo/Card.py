# !/usr/bin/env python3
# -*- coding: utf-8 -*-

class Card:
    ''' 牌 '''
    def __init__(self, value):
        self.value = value
        self.hidden = True  # 默认隐藏

    @property
    def getValue(self):
        """如果牌可见，则返回牌值"""
        return self.value if not self.hidden else -1

    def show(self):
        """使牌永久可见，并返回自身"""
        self.hidden = False
        return self

    def hide(self):
        """使牌隐藏，并返回自身"""
        self.hidden = True
        return self
    
    def __enter__(self):
        """使牌临时可见"""
        self.hidden = False
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        """退出时隐藏牌"""
        self.hidden = True
        


if __name__ == "__main__":
    def card_test():
        # 使用示例
        card = Card(7)
        print("now card is :", card.getValue)  # 隐藏

        card.show()
        print("now card is :", card.getValue)  # 可见
        
        card.hide()
        print("now card is :", card.getValue)  # 隐藏
        
        value = -1 
        
        # 模拟玩家查看卡牌的过程
        with card :
            value = card.getValue
            print("in With card is:", card.getValue)  # 在这个区块内，玩家可以查看卡牌

        print("now card is :", card.getValue)  # 查看后，牌应自动恢复为隐藏或原始状态
        print(value) # 输出value的值(可以带出的值)
    card_test()    