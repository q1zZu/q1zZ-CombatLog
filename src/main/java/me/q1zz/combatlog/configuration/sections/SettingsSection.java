package me.q1zz.combatlog.configuration.sections;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;

public class SettingsSection extends OkaeriConfig {

    @Comment("PL: Czy anty-logout będzie asynchroniczny?")
    @Comment("EN: Should anti-logout will be asynchronous?")
    private boolean async = false;

    @Comment("PL: Czy blokada wchodzenia na spawn będzie włączona? (Wymagany plugin WorldGuard).")
    @Comment("EN: Should spawn will be blocked when fighting? (Require plugin WorldGuard).")
    @CustomKey("spawn-blocked")
    private boolean spawnBlocked = false;

    @Comment("PL: Nazwa regionu WorldGuard na który nie będzie można wejść podczas walki.")
    @Comment("EN: The name of the WorldGuard region that will be blocked when player fighting.")
    @CustomKey("blocked-region-name")
    private String blockedRegionName = "spawn";

    public boolean isAsync() {
        return async;
    }

    public boolean isSpawnBlocked() {
        return spawnBlocked;
    }

    public String getBlockedRegionName() {
        return blockedRegionName;
    }
}
