package me.q1zz.combatlog.listener;

import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import me.q1zz.combatlog.helper.MessageHelper;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final CombatManager combatManager;
    private final PluginConfiguration pluginConfiguration;

    public BlockPlaceListener(CombatManager combatManager, PluginConfiguration pluginConfiguration) {
        this.combatManager = combatManager;
        this.pluginConfiguration = pluginConfiguration;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent event) {

        if(event.isCancelled()) {
            return;
        }

        final Player player = event.getPlayer();

        if(!this.combatManager.isFighting(player.getUniqueId())) {
            return;
        }

        final Block block = event.getBlock();

        if(this.pluginConfiguration.getCombat().getBlockPlaceHeight() < 1) {
            return;
        }

        if(block.getLocation().getY() > this.pluginConfiguration.getCombat().getBlockPlaceHeight()) {
            return;
        }

        event.setCancelled(true);

        MessageHelper.sendMessage(player, this.pluginConfiguration.getMessages().getInteractionBlocked());
    }

}
