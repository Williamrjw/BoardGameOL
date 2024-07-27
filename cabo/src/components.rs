use bevy::prelude::*;

#[derive(Component, Clone)]
pub struct Card {
    pub value: i32,
    pub face_up: bool,
}

#[derive(Component)]
pub struct Player {
    pub hand: Vec<Entity>,
    pub score: i32,
}