package me.q1zz.combatlog.task;

import me.q1zz.combatlog.combat.Combat;
import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import me.q1zz.combatlog.helper.MessageHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.Instant;

public class CombatLogTask extends BukkitRunnable {

    private final CombatManager combatManager;
    private final PluginConfiguration pluginConfiguration;

    public CombatLogTask(CombatManager combatManager, PluginConfiguration pluginConfiguration) {
        this.combatManager = combatManager;
        this.pluginConfiguration = pluginConfiguration;
    }

    @Override
    public void run() {
        for(Combat combat : this.combatManager.getCombats()) {

            final Player player = Bukkit.getPlayer(combat.getUniqueId());
            final Player enemy = combat.getEnemy();

            if(player == null || enemy == null) {
                continue;
            }

            final Instant now = Instant.now();

            if(now.isAfter(combat.getEndOfCombat())) {

                this.combatManager.removeCombat(player.getUniqueId());

                MessageHelper.sendActionBar(player, this.pluginConfiguration.getMessages().getCombatEndActionbar());
                MessageHelper.sendMessage(player, this.pluginConfiguration.getMessages().getCombatEnd());

                continue;
            }

            final Duration combatDuration = Duration.between(Instant.now(), combat.getEndOfCombat());

            String text = this.pluginConfiguration.getMessages().getCombatActionbar()
                    .replace("{TIME}", String.valueOf(combatDuration.toSeconds()));

            MessageHelper.sendActionBar(player, text);
        }
    }
}
