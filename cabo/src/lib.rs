mod components;
mod system;
mod resources;
mod ui;
mod logic;

use wasm_bindgen::prelude::*;
use bevy::prelude::*;
use bevy::render::RenderPlugin;
use bevy::render::settings::{WgpuSettings, RenderCreation, Backends};
use web_sys::window;

#[wasm_bindgen]
pub async fn run() {
    // 设置 panic hook 来改善调试体验
    console_error_panic_hook::set_once();

    // 初始化日志系统
    wasm_logger::init(wasm_logger::Config::default());

    // 创建 Bevy 应用
    let mut app = App::new();

    // 添加插件和系统
    app.add_plugins(DefaultPlugins.set(WindowPlugin {
        primary_window: Some(Window {
            fit_canvas_to_parent: true,
            ..default()
        }),
        ..default()
    }).set(RenderPlugin {
        render_creation: RenderCreation::Automatic(WgpuSettings {
            backends: Some(Backends::GL),
            ..default()
        }),
        synchronous_pipeline_compilation: false,
    }))
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
    ));

    // 运行一次更新以初始化系统
    app.run();
}
