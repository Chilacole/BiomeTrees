# Biome Trees

A Babric mod for Minecraft Better Than Adventure! (BTA) that makes sapling growth biome-aware. 

Instead of adding dozens of custom saplings for every tree variant, this mod allows standard saplings to adapt dynamically to their environment upon generation. If a sapling does not belong to a special biome, its generation remains completely untouched.

**Current Features:**
* **Swamp Trees:** Oak saplings planted in a Swamp biome will naturally grow into mossy Swamp Trees.
* **Tall Birch Trees:** Birch saplings planted in a Birch Forest biome will naturally grow into trees from the native Birch Forest generation pool.

## Requirements
- Minecraft Better Than Adventure! (BTA 8.0+)
- Fabric Loader (Babric)
- [HalpLibe](https://github.com/Turnip-Labs/HalpLibe) (Required Dependency)

## Installation
1. Download the latest `biometrees.jar` from the [Releases](https://github.com/Chilacole/BiomeTrees/releases) tab.
2. Place the `.jar` file into your BTA instance's `mods` folder (e.g., in Prism Launcher or MO2).
3. Ensure you have the required `halplibe` jar in your mods folder as well.
4. Launch the game and use bone meal on your saplings!

---

## Building from Source (For Developers)

If you want to contribute, edit, or compile the mod yourself, follow these steps.

### Prerequisites
- JDK for Java 21 ([Eclipse Temurin](https://adoptium.net/temurin/releases/) recommended)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (Community edition is fine; if using Linux, **DO NOT** use the flatpak distribution)
- Minecraft Development plugin (Optional, but highly recommended)

### Setup instructions

1. Open IntelliJ IDEA and click `Clone Repository` (`Get from VCS` on older versions). Enter this repository's URL: `https://github.com/Chilacole/BiomeTrees`
2. After the project has finished importing, close it and open it again. If the Gradle project does not load automatically, open the right sidebar with `Gradle` on it, open `Tasks` > `fabric` and run `ideaSyncTask`.
3. Open `File` > `Settings` and head to `Build, Execution, Development` > `Build Tools` > `Gradle`. Make sure `Build and run using` and `Run tests using` is set to `Gradle`.
4. Create a new run configuration by going to `Run > Edit Configurations`. Click the plus icon and select `Gradle`. In the `Tasks and Arguments` field, enter `build`. 
5. Running this build task will compile the finished `.jar` file and place it in the `build/libs/` directory.

### Tips

1. If you want to learn more about BTA modding, join the official BTA modding Discord! https://discord.gg/FTUNJhswBT
2. You can test the mod directly in your IDE by selecting the `Minecraft Client` run configuration.
3. You can set your in-game username when launching the client run configuration by adding `--username <username>` to your program arguments.
4. In IntelliJ, you can double-press `Shift` or press `Ctrl+N` to search class files. Change the search scope from `Project Files` to `All Places` to easily explore BTA's decompiled base classes and see how the vanilla biomes work.
