use bevy::prelude::*;
use crate::resources::GameState;
use crate::components::Player;
use crate::components::Card;

#[derive(Component)]
pub struct CardUI {
    pub player: usize,
    pub index: usize,
}

#[derive(Component)]
pub struct DiscardPileMarker;


pub fn setup_ui(
    mut commands: Commands,
    asset_server: Res<AssetServer>,
    mut game_state: ResMut<GameState>,
) {
    // 根节点
    commands
        .spawn(NodeBundle {
            style: Style {
                width: Val::Percent(100.0),
                height: Val::Percent(100.0),
                flex_direction: FlexDirection::Column,
                justify_content: JustifyContent::SpaceBetween,
                ..default()
            },
            ..default()
        })
        .with_children(|parent| {
            // 顶部信息栏
            parent.spawn(TextBundle::from_section(
                "Cabo 游戏",
                TextStyle {
                    font: asset_server.load("fonts/FiraSans-Bold.ttf"),
                    font_size: 40.0,
                    color: Color::WHITE,
                },
            ));

            // 当前玩家信息
            parent.spawn(TextBundle::from_section(
                "当前玩家: 0",
                TextStyle {
                    font: asset_server.load("fonts/FiraSans-Bold.ttf"),
                    font_size: 24.0,
                    color: Color::WHITE,
                },
            ));

            // 中间区域（牌堆和弃牌堆）
            parent
                .spawn(NodeBundle {
                    style: Style {
                        width: Val::Percent(100.0),
                        height: Val::Percent(60.0),
                        justify_content: JustifyContent::SpaceAround,
                        align_items: AlignItems::Center,
                        ..default()
                    },
                    ..default()
                })
                .with_children(|middle| {
                    // 牌堆
                    let deck_entity = middle
                        .spawn((
                            ImageBundle {
                                style: Style {
                                    width: Val::Px(100.0),
                                    height: Val::Px(150.0),
                                    ..default()
                                },
                                image: asset_server.load("BackFace.png").into(),
                                ..default()
                            },
                            CardUI { player: usize::MAX, index: 0 },
                            Interaction::default(),
                        ))
                        .id();
                    game_state.deck_ui_entity = deck_entity;

                    // 弃牌堆
                    let discard_entity = middle
                        .spawn((
                            NodeBundle {
                                style: Style {
                                    width: Val::Px(100.0),
                                    height: Val::Px(150.0),
                                    border: UiRect::all(Val::Px(2.0)),
                                    ..default()
                                },
                                background_color: Color::NONE.into(),
                                border_color: BorderColor(Color::WHITE),
                                ..default()
                            },
                            DiscardPileMarker,
                            CardUI { player: usize::MAX, index: 1 },
                        ))
                        .id();
                    game_state.discard_pile_ui_entity = discard_entity;
                });

            // 玩家手牌区域
            parent
                .spawn(NodeBundle {
                    style: Style {
                        width: Val::Percent(100.0),
                        height: Val::Percent(30.0),
                        justify_content: JustifyContent::Center,
                        align_items: AlignItems::Center,
                        ..default()
                    },
                    ..default()
                })
                .with_children(|hand| {
                    // 假设有4个玩家，每个玩家4张牌
                    for player in 0..4 {
                        for card in 0..4 {
                            hand.spawn((
                                ImageBundle {
                                    style: Style {
                                        width: Val::Px(80.0),
                                        height: Val::Px(120.0),
                                        margin: UiRect::all(Val::Px(5.0)),
                                        ..default()
                                    },
                                    image: asset_server.load("BackFace.png").into(),
                                    ..default()
                                },
                                CardUI { player, index: card },
                            ));
                        }
                    }
                });
        });
}

pub fn update_ui(
    game_state: Res<GameState>,
    players: Query<&Player>,
    mut card_images: Query<(&mut UiImage, &CardUI)>,
    mut text_query: Query<&mut Text>,
    asset_server: Res<AssetServer>,
    cards: Query<&Card>,
    mut discard_pile_query: Query<(&mut BackgroundColor, &mut BorderColor), With<DiscardPileMarker>>,
) {
    // 更新当前玩家信息
    if let Ok(mut text) = text_query.get_single_mut() {
        text.sections[0].value = format!("当前玩家: {}", game_state.current_player);
    }

    // 更新玩家手牌显示
    for (player_index, player_entity) in game_state.players.iter().enumerate() {
        if let Ok(player) = players.get(*player_entity) {
            for (card_index, &card_entity) in player.hand.iter().enumerate() {
                if let Ok((mut ui_image, card_ui)) = card_images.get_mut(card_entity) {
                    if card_ui.player == player_index && card_ui.index == card_index {
                        if let Ok(card) = cards.get(card_entity) {
                            if card.face_up {
                                // 显示牌面
                                ui_image.texture = asset_server.load(format!("textures/card_{}.png", card.value));
                            } else {
                                // 显示牌背
                                ui_image.texture = asset_server.load("BackFace.png");
                            }
                        }
                    }
                }
            }
        }
    }

    // 更新弃牌区
    if let Ok((mut background_color, mut border_color)) = discard_pile_query.get_single_mut() {
        if let Some(&top_card) = game_state.discard_pile.last() {
            if let Ok(card) = cards.get(top_card) {
                // 显示顶部卡牌
                *background_color = BackgroundColor(Color::WHITE);
                border_color.0 = Color::BLACK;
                
                // 如果需要显示卡牌图像，可以这样做：
                if let Ok((mut ui_image, _)) = card_images.get_mut(game_state.discard_pile_ui_entity) {
                    ui_image.texture = asset_server.load(format!("textures/card_{}.png", card.value));
                }
            }
        } else {
            // 空弃牌堆
            *background_color = BackgroundColor(Color::NONE);
            border_color.0 = Color::WHITE;
            
            // 如果需要显示空弃牌堆的图像，可以这样做：
            if let Ok((mut ui_image, _)) = card_images.get_mut(game_state.discard_pile_ui_entity) {
                ui_image.texture = asset_server.load("textures/empty_discard.png");
            }
        }
    }
}