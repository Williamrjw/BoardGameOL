use bevy::prelude::*;
use bevy::input::keyboard::KeyCode;
use bevy::input::mouse::MouseButton;
use crate::resources::GameState;
use crate::components::Player;
use crate::ui::CardUI;

pub fn setup(mut commands: Commands, mut game_state: ResMut<GameState>) {
    commands.spawn(Camera2dBundle::default());
    for i in 0..4 {  // 假设有4个玩家
        let player_entity = commands.spawn((
            Player { hand: Vec::new(), score: 0 },
            // 可以添加其他玩家相关的组件
        )).id();
        game_state.players.push(player_entity);
    }
}

pub fn update_game_state() {
    // 更新游戏状态的逻辑
}

pub fn handle_input(mut commands: Commands,
    buttons: Res<ButtonInput<MouseButton>>,
    keys: Res<ButtonInput<KeyCode>>,
    mut game_state: ResMut<GameState>,
    card_query: Query<(Entity, &CardUI), With<Interaction>>,) {
        for (entity, card_ui) in card_query.iter() {
            if buttons.just_pressed(MouseButton::Left) {
                println!("单击左键");
                game_state.select_card(card_ui.player, card_ui.index);
            }
        }
    
        if keys.just_pressed(KeyCode::Space) {
            game_state.end_turn();
        }
    // 可以添加更多的输入处理逻辑
}