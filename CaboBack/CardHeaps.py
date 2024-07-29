# !/usr/bin/env python3
# -*- coding: utf-8 -*-

from Card import Card
from random import shuffle

class CardHeap:
    ''' 摸牌堆 ''' 
    def __init__(self):
        self.cards = [Card(_) for _ in range(14)]
        self.cards += [Card(_) for _ in range(14)]
        self.cards += [Card(_) for _ in range(13)]
        self.cards += [Card(_) for _ in range(13)]
        # 洗牌
        shuffle(self.cards)
        
    def draw(self):
        return self.cards.pop() if self.cards else None
    
    def getSize(self):
        return len(self.cards)
    

    
class ThrownCardsHeap:
    ''' 弃牌堆 '''
    def __init__(self):
        self.cards = []
        self.size = len(self.cards)
        
    def throw(self, card):
        card.show()
        self.cards.append(card)
        
    def pickRubish(self):
        return self.cards.pop() if self.cards else None
    
    def top(self):
        return self.cards[-1] if self.cards else None
    
if __name__ == '__main__':
    def card_heap_test():
        ch = CardHeap()
        print(ch.getSize())
        for i in ch.cards: print(i.value,end=",")
        print("")
        print(ch.draw().value)
        for i in ch.cards: print(i.value,end=",")
        print("")
        print(ch.getSize())
        ch.cards=[]
        print(ch.draw())
        

    
    def thrown_cards_heap_test():
        tc = ThrownCardsHeap()
        print(tc.top())
        tc.throw(Card(1))
        print(tc.top().value)
        tc.throw(Card(2))
        print(tc.top().value)
        print(tc.pickRubish().value)
        print(tc.top().value)
        print(tc.pickRubish().value)
        print(tc.top())
        print(tc.pickRubish())
       
    card_heap_test() 
    thrown_cards_heap_test()