package me.q1zz.combatlog.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import me.q1zz.combatlog.configuration.sections.CombatSection;
import me.q1zz.combatlog.configuration.sections.MessagesSection;
import me.q1zz.combatlog.configuration.sections.SettingsSection;

public class PluginConfiguration extends OkaeriConfig {

    @Comment("PL: Ustawienia pluginu.")
    @Comment("EN: Plugin settings.")
    private SettingsSection settings = new SettingsSection();

    @Comment("PL: Ustawienia walki.")
    @Comment("EN: Combat settings.")
    private CombatSection combat = new CombatSection();

    @Comment("PL: Ustawienia wiadomości.")
    @Comment("EN: Messages configuration.")
    private MessagesSection messages = new MessagesSection();

    public CombatSection getCombat() {
        return combat;
    }

    public SettingsSection getSettings() {
        return settings;
    }

    public MessagesSection getMessages() {
        return messages;
    }
}
