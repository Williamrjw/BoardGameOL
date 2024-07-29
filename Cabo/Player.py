# !/usr/bin/env python3
# -*- coding: utf-8 -*-

import uuid


class Player:
    def __init__(self, name):
        # 利用UUID生成一个用户id
        self.id = uuid.uuid4().hex
        self.name = name
        self.hand = []
        self.score = 0
    
    def getInitCards(self, cardHeap):
        ''' 初始化玩家手牌 '''
        self.hand = [cardHeap.draw() for _ in range(4)]
    
    def Peek(self, index):
        """玩家查看自己的一张牌"""
        if self.knowledge[index] is None:
            self.knowledge[index] = self.hand[index].value
            return self.hand[index].value
        return None

    def Spy(self, opponent, index):
        """查看对手的一张牌（假设此操作通过游戏规则实现）"""
        if 0 <= index < len(opponent.hand):
            return opponent.hand[index].value
        return None

    def Swap(self, opponent, my_index, their_index):
        """交换自己和对手的一张牌"""
        if 0 <= my_index < len(self.hand) and 0 <= their_index < len(opponent.hand):
            self.hand[my_index], opponent.hand[their_index] = opponent.hand[their_index], self.hand[my_index]
            return True
        return False
    
    def calcScore(self):
        """计算得分"""
        for card in self.hand:
            card.show()
        return sum([card.value for card in self.hand])
    
    def SwapWith(self, card, indexs):
        """将自己手上的一定数量牌与该牌交换"""
        pass
    
if __name__ == '__main__':
    player = Player('test')
    print(player.id)
    print(player.name)
    print(player.hand)
    print(player.score)

    print(player.calcScore())