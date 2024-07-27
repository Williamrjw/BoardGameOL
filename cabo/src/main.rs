mod components;
mod system;
mod resources;
mod ui;
mod logic;

use bevy::prelude::*;

fn main() {
    App::new()
        .add_plugins(DefaultPlugins)
        .insert_resource(resources::GameState::default())
        .add_systems(Startup, (
            system::setup,
            ui::setup_ui,
        ))
        .add_systems(Update, (
            system::update_game_state,
            system::handle_input,
            ui::update_ui,
            logic::player_turn,
            // logic::calculate_score
        ))
        .run();
}