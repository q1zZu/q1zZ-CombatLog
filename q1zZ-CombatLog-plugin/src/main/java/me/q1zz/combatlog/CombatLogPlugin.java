package me.q1zz.combatlog;

import me.q1zz.combatlog.message.MessageAnnouncer;
import me.q1zz.combatlog.util.legacy.LegacyColorProcessor;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class CombatLogPlugin extends JavaPlugin {

    private BukkitAudiences bukkitAudiences;

    @Override
    public void onEnable() {

        this.bukkitAudiences = BukkitAudiences.create(this);
        final MiniMessage miniMessage = MiniMessage.builder()
                .postProcessor(new LegacyColorProcessor())
                .build();
        final MessageAnnouncer messageAnnouncer = new MessageAnnouncer(this.bukkitAudiences, miniMessage);

    }

    @Override
    public void onDisable() {

        if(this.bukkitAudiences != null) {
            this.bukkitAudiences.close();
        }

    }

}
