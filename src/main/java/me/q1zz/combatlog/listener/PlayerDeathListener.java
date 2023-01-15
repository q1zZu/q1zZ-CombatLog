package me.q1zz.combatlog.listener;

import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import me.q1zz.combatlog.helper.MessageHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final CombatManager combatManager;
    private final PluginConfiguration pluginConfiguration;

    public PlayerDeathListener(CombatManager combatManager, PluginConfiguration pluginConfiguration) {
        this.combatManager = combatManager;
        this.pluginConfiguration = pluginConfiguration;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent event) {

        final Player player = event.getEntity();

        if(!this.combatManager.isFighting(player.getUniqueId())) {
            return;
        }

        final Player enemy = this.combatManager.getEnemy(player.getUniqueId());

        if(!this.combatManager.isFighting(enemy.getUniqueId())) {
            return;
        }

        this.combatManager.removeCombat(player.getUniqueId());
        this.combatManager.removeCombat(enemy.getUniqueId());

        MessageHelper.sendMessage(enemy, this.pluginConfiguration.getMessages().getCombatEnd());
        MessageHelper.sendActionBar(enemy, this.pluginConfiguration.getMessages().getCombatEndActionbar());
    }

}
