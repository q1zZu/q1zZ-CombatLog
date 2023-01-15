package me.q1zz.combatlog.listener;

import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import me.q1zz.combatlog.helper.MessageHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryOpenListener implements Listener {

    private final CombatManager combatManager;
    private final PluginConfiguration pluginConfiguration;

    public InventoryOpenListener(CombatManager combatManager, PluginConfiguration pluginConfiguration) {
        this.combatManager = combatManager;
        this.pluginConfiguration = pluginConfiguration;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryOpenEvent event) {

        if(event.isCancelled()) {
            return;
        }

        if(!(event.getPlayer() instanceof final Player player)) {
            return;
        }

        if(!this.combatManager.isFighting(player.getUniqueId())) {
            return;
        }

        if(!this.pluginConfiguration.getCombat().isDisableInventories()) {
            return;
        }

        event.setCancelled(true);

        MessageHelper.sendMessage(player, this.pluginConfiguration.getMessages().getInteractionBlocked());
    }

}
