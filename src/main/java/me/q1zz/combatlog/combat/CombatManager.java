package me.q1zz.combatlog.combat;

import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.*;

public class CombatManager {

    private final Map<UUID, Combat> combats = new HashMap<>();

    public List<Combat> getCombats() {
        return new ArrayList<>(this.combats.values());
    }

    public Player getEnemy(UUID uuid) {
        if(!this.isFighting(uuid)) {
            return null;
        }
        return this.combats.get(uuid).getEnemy();
    }

    public boolean isFighting(UUID uuid) {
        return this.combats.containsKey(uuid);
    }

    public void removeCombat(UUID uuid) {
        this.combats.remove(uuid);
    }

    public void addCombatLog(Player player, Player enemy, Duration time) {

        if(this.isFighting(player.getUniqueId())) {
            this.removeCombat(player.getUniqueId());
        }

        final Combat combat = new Combat(player.getUniqueId(), enemy.getUniqueId(), time.toSeconds());

        this.combats.put(player.getUniqueId(), combat);
    }

}
