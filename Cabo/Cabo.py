# !/usr/bin/env python3
# -*- coding: utf-8 -*-

from Card import Card
from CardHeaps import CardHeap, ThrownCardsHeap
from Player import Player
from functools import reduce


class CaboGame:
    def __init__(self, players):
        self.players = players
        self.deck = CardHeap()
        self.discard_pile = ThrownCardsHeap()
        self.turn = -1
        self.current_player = self.players[self.turn]
        self.cabo_caller = None
        
    def nextTurn(self):
        # 轮到下一位玩家
        self.turn = (self.turn + 1) % len(self.players)
        self.current_player = self.players[self.turn]
        
    def isOver(self):
        """游戏结束判定"""
        return self.current_player == self.cabo_caller or self.deck.cards.isEmpty()
    
    def calcScore(self):
        """计算得分"""
        min_score = self.cabo_caller.calcScore()
        for player in self.players:
            if self.cabo_caller != player:
                min_score = min(min_score, player.score)
        for player in self.players:
            if self.cabo_caller != player:
                player.score += reduce(lambda x, y: x + y, player.hand)
    
    def callCabo(self):
        """玩家呼唤卡波"""
        self.cabo_caller = self.current_player

    def play(self):
        """玩家回合操作"""
        choice = IO.getCommand("""请输入指令：
                               【1】从牌堆顶摸牌
                               【2】从弃牌堆顶摸牌
                               【3】呼唤Cabo
                               """)
        if choice == "1":
            card = self.deck.draw()
            if card is not None:
                throwFlag = False
                with card:
                    IO.output(card.getValue)
                    if card.getValue == 7 or card.getValue == 8:
                        if IO.getYesOrNo():
                            self.current_player.Peek()
                    elif card.getValue == 9 or card.getValue == 10:
                        if IO.getYesOrNo():
                            self.current_player.Spy()
                    elif card.getValue == 11 or card.getValue == 12:
                        if IO.getYesOrNo():
                            self.current_player.Swap()
                    elif IO.getYesOrNo("是否与自己的不定数量手牌交换？"):
                        self.current_player.SwapWith()
                    else:
                        throwFlag = True
                if throwFlag:
                    # 弃置该牌
                    self.discard_pile.throw(card)
        elif choice == "2":
            card = self.discard_pile.pickRubish()
            if card is not None:
                with card:
                    IO.output(card.getValue)
                    if IO.getYesOrNo("是否与自己的不定数量手牌交换？"):
                        self.current_player.SwapWith()
                    else:
                        throwFlag = True
                if throwFlag:
                    # 弃置该牌
                    self.discard_pile.throw(card)
            if card is not None:
                if IO.getYesOrNo():
                    pass
        elif choice == "3":
            self.callCabo()
        else:
            IO.output("指令错误")
        
    def run(self):
        """完整流程"""
        # 初始化手牌
        for player in self.players:
            player.getInitCards(self.deck)
            # 看自己的两张牌
            player.Peek(IO.getIndex())
            player.Peek(IO.getIndex())
        # 翻出牌堆顶的一张牌到弃牌堆
        self.discard_pile.throw(self.deck.draw())
        # 主流程开始
        while True:
            # 初始是-1，所以最开始要轮一下
            self.nextTurn()
            
            # 玩家操作
            self.play(self.current_player, IO.getIndex())
            # 玩家出牌
            card = self.current_player.play()
            if card is None:
                break
            self.discard_pile.throw(card)
            
            # 检查是否胜利
            if self.isOver():
                IO.output("游戏结束，" + self.cabo_caller.getName + "获胜！")
                return
                

                
            
class IO:
    def __init__(self, player):
        self.player = player
    
    @staticmethod
    def getIndex(prompt="请输入索引"):
        # return random.randint(0, 4)
        return int(input(prompt))
    

    def getIndexs(prompt="请输入索引，以空格分割："):
        return [int(i) for i in input(prompt).split()]
    
    @staticmethod
    def getCommand(prompt="请输入指令："):
        # return random.choice(["drawFromCardHeap", "drawFromThrownCardsHeap", "callCabo"])
        return input(prompt)
    
    @staticmethod
    def output(message):
        print(message)
        
    @staticmethod
    def getYesOrNo(prompt="是否发动技能？(y/n)"):
        # return random.choice([True, False])
        check = input(prompt)
        if check == "y" or check == "Y":
            return True
        return False
    
print(IO.getIndexs())


