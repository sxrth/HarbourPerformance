# ObsidianOptimization 1.6.3

Compatibility-first Fabric client foundation.

Build target:
- Minecraft 1.21.11
- Yarn 1.21.11+build.6
- Fabric Loom 1.12.1
- Gradle 8.14
- Java 21

The build intentionally has no Fabric API dependency, because the previous build failed during Loom's mapping setup while processing `fabric-content-registries-v0`.

No renderer Mixins are installed. Common optimization mods are detected read-only.
