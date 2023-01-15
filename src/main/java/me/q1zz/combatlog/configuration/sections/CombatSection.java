package me.q1zz.combatlog.configuration.sections;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;

import java.util.Arrays;
import java.util.List;

public class CombatSection extends OkaeriConfig {

    @Comment("PL: Czas walki w sekundach.")
    @Comment("EN: Combat time in seconds.")
    private int time = 30;

    @Comment("PL: Czy otwieranie inventory (skrzynki, enderchest, crafting, itd) powinno być wyłączone podczas walki?")
    @Comment("EN: Should opening inventories (chests, enderchest, crafting, etc) will be disabled when fighting?")
    @CustomKey("disable-inventories")
    private boolean disableInventories = true;

    @Comment("PL: Wysokość od której można stawiać bloki podczas walki. (Aby wyłączyć: -1)")
    @Comment("EN: Height from player can place blocks when fighting. (-1 to disable).")
    @CustomKey("block-place-height")
    private int blockPlaceHeight = 60;

    @Comment("PL: Dozwolone komendy podczas walki.")
    @Comment("EN: Allowed commands when fighting.")
    @CustomKey("allowed-commands")
    private List<String> allowedCommands = Arrays.asList("helpop", "msg", "r", "reply");

    @Comment("PL: Permisja która pozwala na ominięcie anty-logoutu.")
    @Comment("EN: Permission for bypass anti-logout.")
    @CustomKey("bypass-permission")
    private String bypassPermission = "q1zz.combatlog.bypass";

    public int getCombatTime() {
        return time;
    }

    public boolean isDisableInventories() {
        return disableInventories;
    }

    public int getBlockPlaceHeight() {
        return blockPlaceHeight;
    }

    public List<String> getAllowedCommands() {
        return allowedCommands;
    }

    public String getBypassPermission() {
        return bypassPermission;
    }
}
