BossBarHealth
=============
This plugin is for PvP servers.  When PlayerA attacks PlayerB, PlayerA gets the health bar of PlayerB displayed at the top of the screen in the form of the Ender Dragon health bar.

Config
------
Config file is a yml with four settings:
- `expireticks` (integer; the amount of ticks where, if PlayerA does not attack anyone, the Boss bar will disappear)
- `enabled` (boolean (true/false); specifies whether BossBarHealth is enabled)
- `prefix` (BossBar label prefix, used to customize BossBar label)
- `suffix` (BossBar label suffix, used to customize BossBar label)

Permissions
-----------
- `bossbar.*` gives access to BossBarHealth as well as its config command `bossbar`
- `bossbar.use` allows a user to use BossBarHealth
- `bossbar.config` allows a user to configure BossBarHealth