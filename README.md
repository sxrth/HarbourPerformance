# ObsidianOptimization 1.6.3

Compatibility-first Fabric client foundation.

- Minecraft 1.21.11
- Yarn 1.21.11+build.6
- Fabric Loom 1.12.1
- Gradle 8.14
- Java 21
- **No Fabric API dependency**
- **No renderer Mixins**
- **No particle/entity Mixins**
- Detects Sodium/Iris/Lithium/ImmediatelyFast/FerriteCore/EntityCulling without modifying them

This build intentionally minimizes the dependency graph so Loom does not process Fabric API's
`fabric-content-registries-v0` Javadoc artifact during Minecraft setup.
