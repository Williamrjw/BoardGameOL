use bevy::prelude::*;
use rand::seq::SliceRandom;
use crate::components::{Card, Player};
use crate::resources::GameState;

pub fn deal_cards(
    mut commands: Commands,
    mut game_state: ResMut<GameState>,
    mut players: Query<(Entity, &mut Player)>,
) {
    // 1. 创建一副新牌
    let mut deck: Vec<Card> = (1..=13).flat_map(|value| std::iter::repeat(value).take(4))
        .map(|value| Card { value, face_up: false })
        .collect();

    // 2. 洗牌
    let mut rng = rand::thread_rng();
    deck.shuffle(&mut rng);

    // 3. 发牌给每个玩家
    for (player_entity, mut player) in players.iter_mut() {
        for i in 0..4 {
            if let Some(card) = deck.pop() {
                let card_entity = commands.spawn((
                    card.clone(),
                    Transform::default(), // 你可能需要根据玩家位置设置不同的变换
                    GlobalTransform::default(),
                )).id();

                // 设置前两张牌为正面朝上
                if i < 2 {
                    commands.entity(card_entity).insert(Card { face_up: true, ..card });
                }

                player.hand.push(card_entity);
            }
        }
    }

    // 4. 剩余的牌放入牌堆
    game_state.deck = deck.into_iter()
        .map(|card| commands.spawn((card, Transform::default(), GlobalTransform::default())).id())
        .collect();

    // 5. 初始化弃牌堆
    game_state.discard_pile = Vec::new();
}

pub fn player_turn(
    mut commands: Commands,
    mut game_state: ResMut<GameState>,
    mut players: Query<(Entity, &mut Player)>,
    cards: Query<&Card>,
) {
    let current_player_entity = game_state.players[game_state.current_player];
    let (_, mut current_player) = players.get_mut(current_player_entity).expect("当前玩家应该存在");

    // 1. 抽一张牌
    if let Some(drawn_card) = game_state.deck.pop() {
        // 2. 玩家选择保留抽到的牌或从弃牌堆顶抽一张
        // 这里简化为总是选择新抽到的牌
        current_player.hand.push(drawn_card);

        // 3. 玩家可以选择交换手牌中的一张牌或弃掉抽到的牌
        // 这里简化为总是弃掉最后一张牌（即刚抽到的牌）
        let discarded_card = current_player.hand.pop().unwrap();
        game_state.discard_pile.push(discarded_card);

        // 4. 检查是否触发特殊效果（比如数字为7或8的牌）
        if let Ok(card) = cards.get(discarded_card) {
            match card.value {
                7 | 8 => {
                    // 实现查看其他玩家手牌的逻辑
                    println!("玩家可以查看其他玩家的一张手牌");
                },
                _ => {}
            }
        }
    }

    // 5. 切换到下一个玩家
    game_state.current_player = (game_state.current_player + 1) % game_state.players.len();
}

pub fn calculate_score(players: Query<(Entity, &Player)>, cards: Query<&Card>) {
    for (entity, player) in players.iter() {
        let mut score = 0;
        for &card_entity in &player.hand {
            if let Ok(card) = cards.get(card_entity) {
                score += match card.value {
                    13 => 13,  // 王牌
                    12 => 10,  // Q
                    11 => 10,  // J
                    10 => 10,  // 10
                    1..=9 => card.value,
                    _ => 0,    // 这种情况不应该发生
                };
            }
        }
        println!("玩家 {:?} 的得分: {}", entity, score);
    }
}