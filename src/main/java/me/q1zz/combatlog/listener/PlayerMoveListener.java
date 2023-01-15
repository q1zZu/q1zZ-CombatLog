package me.q1zz.combatlog.listener;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import me.q1zz.combatlog.helper.MessageHelper;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class PlayerMoveListener implements Listener {

    private final CombatManager combatManager;
    private final PluginConfiguration pluginConfiguration;
    private final WorldGuard worldGuard;

    public PlayerMoveListener(CombatManager combatManager, PluginConfiguration pluginConfiguration, WorldGuard worldGuard) {
        this.combatManager = combatManager;
        this.pluginConfiguration = pluginConfiguration;
        this.worldGuard = worldGuard;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {

        if(event.isCancelled()) {
            return;
        }

        if(!this.pluginConfiguration.getSettings().isSpawnBlocked()) {
            return;
        }

        if(this.worldGuard == null) {
            return;
        }

        final Player player = event.getPlayer();

        if(!this.combatManager.isFighting(player.getUniqueId())) {
            return;
        }


        for(ProtectedRegion region : this.worldGuard.getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(player.getLocation()))) {

            if(!region.getId().equalsIgnoreCase(this.pluginConfiguration.getSettings().getBlockedRegionName())) {
                continue;
            }

            Objects.requireNonNull(player.getLocation().getWorld(), "Player world cannot be null!");

            player.setVelocity(player.getLocation().getDirection().normalize().setY(0).multiply(-1));
            player.getLocation().getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 3);
            player.getLocation().getWorld().playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 3);

            MessageHelper.sendMessage(player, this.pluginConfiguration.getMessages().getRegionBlocked());

            break;
        }

    }

}
