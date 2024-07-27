use bevy::prelude::*;


#[derive(Resource)]
pub struct GameState {
    pub current_player: usize,
    pub players: Vec<Entity>, 
    pub deck: Vec<Entity>,
    pub discard_pile: Vec<Entity>,
    pub deck_ui_entity: Entity,
    pub discard_pile_ui_entity: Entity,
}

// 为 GameState 实现 Default trait
impl Default for GameState {
    fn default() -> Self {
        Self {
            current_player: 0,
            players: Vec::new(),
            deck: Vec::new(),
            discard_pile: Vec::new(),
            deck_ui_entity: Entity::PLACEHOLDER,
            discard_pile_ui_entity: Entity::PLACEHOLDER,
        }
    }
}

impl GameState {
    pub fn end_turn(&mut self) {
        // 实现结束回合的逻辑
        self.current_player = (self.current_player + 1) % self.players.len();
        // 可能还需要其他逻辑，如抽牌等
    }

    pub fn select_card(&mut self, player: usize, card_index: usize) {
        // 实现选择卡牌的逻辑

        // 例如，翻开卡牌，或者执行特定的游戏规则
    }
}