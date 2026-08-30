# HarbourPerformance 1.1.0

Fabric 1.21.x client performance helper focused on FPS stability and low frametime while avoiding aggressive renderer overwrites.

## Controls
- **F8** — Performance Mode
- **F9** — Adaptive FPS

Performance Mode saves the current render distance, simulation distance and FPS limit, then applies a conservative low-load profile. Adaptive mode adjusts render distance from FPS feedback and never exceeds the distance saved before enabling the mode.

## Compatibility
The mod intentionally avoids renderer overwrites and does not bundle or replace Sodium, Iris, ImmediatelyFast, Entity Culling, Lithium, FerriteCore or Indium. It only detects those mods for diagnostics. This reduces conflict risk, but no Minecraft mod can guarantee zero conflicts with every future mod.

## Build
Push to GitHub and run **Actions -> Build HarbourPerformance**. The workflow builds the 1.21.x matrix as separate artifacts because Fabric/Minecraft APIs and mappings vary between releases.
