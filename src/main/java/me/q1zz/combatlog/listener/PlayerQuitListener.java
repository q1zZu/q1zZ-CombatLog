package me.q1zz.combatlog.listener;

import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import me.q1zz.combatlog.helper.MessageHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.stream.Collectors;

public class PlayerQuitListener implements Listener {

    private final CombatManager combatManager;
    private final PluginConfiguration pluginConfiguration;

    public PlayerQuitListener(CombatManager combatManager, PluginConfiguration pluginConfiguration) {
        this.combatManager = combatManager;
        this.pluginConfiguration = pluginConfiguration;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {

        final Player player = event.getPlayer();

        if(!this.combatManager.isFighting(player.getUniqueId())) {
            return;
        }

        final Player enemy = this.combatManager.getEnemy(player.getUniqueId());

        if(enemy == null) {
            return;
        }

        this.combatManager.removeCombat(enemy.getUniqueId());
        this.combatManager.removeCombat(player.getUniqueId());

        player.setHealth(0.0);

        MessageHelper.sendMessage(enemy, this.pluginConfiguration.getMessages().getCombatEnd());
        MessageHelper.sendActionBar(enemy, this.pluginConfiguration.getMessages().getCombatEndActionbar());

        MessageHelper.broadcastMessage(this.pluginConfiguration.getMessages().getLogoutBroadcast().stream().map(line -> line.replace("{PLAYER}", player.getName())).collect(Collectors.toList()));
    }

}
