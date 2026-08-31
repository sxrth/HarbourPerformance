# ObsidianOptimization 1.6.6

Compatibility-first Fabric client optimization foundation.

Build target:
- Minecraft 1.21.11
- Fabric Loom 1.12.1
- Gradle 8.14
- Java 21
- Yarn 1.21.11+build.6
- Fabric Loader 0.18.2

This build intentionally has no Fabric API dependency and no Mixins. It only uses Fabric Loader APIs,
which prevents Fabric API module Javadocs (including fabric-content-registries-v0) from entering Loom's
Minecraft setup while keeping the mod compatible with Sodium/Iris/Lithium and other client mods.
