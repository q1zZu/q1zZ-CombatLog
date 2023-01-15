package me.q1zz.combatlog.configuration.sections;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessagesSection extends OkaeriConfig {

    @Comment("PL: Tekst na actionbarze gdy gracz jest podczas walki.")
    @Comment("EN: Text on the actionbar when player fighting.")
    @CustomKey("combat-actionbar")
    private String combatActionbar = "&4&lANTI-LOGOUT &8» &c{TIME}s";

    @Comment("PL: Tekst na actionbarze gdy gracz zakończy walkę.")
    @Comment("EN: Text on the actionbar when player stop fighting.")
    @CustomKey("combat-actionbar-end")
    private String combatEndActionbar = "&cYour fight is end!";

    @Comment("PL: Wiadomość gdy gracz zakończy walkę.")
    @Comment("EN: Message when player stop fighting.")
    @CustomKey("combat-end")
    private List<String> combatEnd = Collections.singletonList("&cYour fight is end!");

    @Comment("PL: Wiadomość gdy gracz próbuję wejść na zablokowany region.")
    @Comment("EN: Message when player trying to enter blocked region.")
    @CustomKey("region-blocked")
    private List<String> regionBlocked = Collections.singletonList("&cYou cannot enter to this region when fighting!");

    @Comment("PL: Wiadomość gdy interakcja została zablokowana.")
    @Comment("EN: Message when interaction has been blocked.")
    @CustomKey("interaction-blocked")
    private String interactionBlocked = "&cYou can't do that when fighting!";

    @Comment("PL: Wiadomość gdy gracz wyloguje się z serwera podczas walki.")
    @Comment("EN: Message when player logout from server during fighting.")
    @CustomKey("logout-broadcast")
    private List<String> logoutBroadcast = Collections.singletonList("&cPlayer &7{PLAYER} &clogout from the server during fight!");

    public String getCombatActionbar() {
        return combatActionbar;
    }

    public List<String> getCombatEnd() {
        return combatEnd;
    }

    public String getCombatEndActionbar() {
        return combatEndActionbar;
    }

    public List<String> getRegionBlocked() {
        return regionBlocked;
    }

    public String getInteractionBlocked() {
        return interactionBlocked;
    }

    public List<String> getLogoutBroadcast() {
        return logoutBroadcast;
    }
}
