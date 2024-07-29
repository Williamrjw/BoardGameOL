# 关于cabo游戏客户端
## 介绍

基于bevy制作的可以运行在web的cabo游戏

**系统需求**

* rust
* wasm-pack

## 技术选型

  * bevy 0.14.0
  * rand 0.8.5
  * wasm-bindgen 0.2.92

## 项目运行
**注意默认源需要使用vpn**

### 直接运行
```
cargo run
```

### 编译为web项目
#### mac环境下编译

```
cargo install wasm-pack
brew install binaryen
wasm-pack build --target web
```
#### windows环境下编译
如果想简单运行，则在Cargo.toml中添加
```
[package.metadata.wasm-pack.profile.release]
wasm-opt = false
```
添加这个内容会导致打包成web项目时，没有优化，导致项目性能下降

如果想要则自行安装windows版[binaryen](https://github.com/WebAssembly/binaryen/releases)

随后输入指令

```
cargo install wasm-pack
wasm-pack build --target web
```



### web项目运行
在项目根目录运行
```
npm install -g http-server
http-server

```

这里是临时运行了一个小型的http服务，也可以考虑直接将编译后的pkg以及index.html拷贝到静态资源服务器上直接运行(httpd或nginx)