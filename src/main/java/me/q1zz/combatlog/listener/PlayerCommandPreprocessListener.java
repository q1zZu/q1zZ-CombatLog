package me.q1zz.combatlog.listener;

import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import me.q1zz.combatlog.helper.MessageHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {

    private final CombatManager combatManager;
    private final PluginConfiguration pluginConfiguration;

    public PlayerCommandPreprocessListener(CombatManager combatManager, PluginConfiguration pluginConfiguration) {
        this.combatManager = combatManager;
        this.pluginConfiguration = pluginConfiguration;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        if(event.isCancelled()) {
            return;
        }

        final Player player = event.getPlayer();

        if(!this.combatManager.isFighting(player.getUniqueId())) {
            return;
        }

        final String command = event.getMessage();

        if(this.pluginConfiguration.getCombat().getAllowedCommands().contains(command)) {
            return;
        }

        event.setCancelled(true);

        MessageHelper.sendMessage(player, this.pluginConfiguration.getMessages().getInteractionBlocked());
    }

}
