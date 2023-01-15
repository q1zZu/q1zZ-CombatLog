package me.q1zz.combatlog.listener;

import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.time.Duration;

public class EntityDamageByEntityListener implements Listener {

    private final CombatManager combatManager;
    private final PluginConfiguration pluginConfiguration;

    public EntityDamageByEntityListener(CombatManager combatManager, PluginConfiguration pluginConfiguration) {
        this.combatManager = combatManager;
        this.pluginConfiguration = pluginConfiguration;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if(event.isCancelled()) {
            return;
        }

        if(!(event.getDamager() instanceof final Player enemy)) {
            return;
        }

        if(!(event.getEntity() instanceof final Player player)) {
            return;
        }

        final Duration combatDuration = Duration.ofSeconds(this.pluginConfiguration.getCombat().getCombatTime());

        if(!player.hasPermission(this.pluginConfiguration.getCombat().getBypassPermission())) {
            this.combatManager.addCombatLog(player, enemy, combatDuration);
        }
        if(!enemy.hasPermission(this.pluginConfiguration.getCombat().getBypassPermission())) {
            this.combatManager.addCombatLog(enemy, player, combatDuration);
        }

    }

}
