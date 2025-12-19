# Tinkers' Gearbox
## 项目介绍
Tinkers' Gearbox（简称 TGearbox）是一款为《我的世界》Forge 模组打造的辅助类库（Lib），专门为 Create（机械动力） 和 Tinkers' Construct（匠魂，简称 TiC） 两大模组提供功能扩展与通用 API 支持，旨在简化开发者对两款模组核心物品、方块的二次开发，减少重复编码工作。  
该库不直接向玩家提供可交互内容，仅作为其他模组的依赖项存在，为模组开发者提供便捷的扩展能力。
## 核心功能
当前版本已实现的核心扩展能力如下：
- 冶炼炉 / 合金炉扩展燃料模块 & 接口
- 风扇处理目标接口
## 前置依赖
使用 TGearbox 作为依赖的模组，需确保运行环境中已安装以下前置模组（对应开发环境需引入对应依赖）：
- Forge
- Create
- Tinkers' Construct
## 开发者使用指南
### 引入依赖
```buildscript {
repositories {
    // KTT 官方仓库
    maven { url "https://maven.kessokuteatime.work/releases" }
}
dependencies {
    modImplementation "com.chemiofitor:tgearbox:1.0"
}
```
你可以在[KTTMaven](https://maven.kessokuteatime.work/#/releases/com/chemiofitor/tgearbox)找到最新版本的TGearbox，
### 使用API
已提供Javadoc，具体使用示例可参考该库的源码注释和[匠械](https://github.com/HO-Artisan/TinkersGears)。
## 许可证
本项目采用 MIT 许可证 开源，详情请查看 [LICENSE](LICENSE) 文件。
## 致谢
感谢 Create 与 TiC 提供的优秀模组生态与开放 API；  
感谢 KessokuTeaTime 团队提供的 Maven 仓库支持。