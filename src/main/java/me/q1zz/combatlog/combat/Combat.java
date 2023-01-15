package me.q1zz.combatlog.combat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.UUID;

public class Combat {

    private final UUID uniqueId;
    private final UUID enemyUniqueId;
    private final Instant endOfCombat;

    public Combat(UUID uniqueId, UUID enemyUniqueId, long time) {
        this.uniqueId = uniqueId;
        this.enemyUniqueId = enemyUniqueId;
        this.endOfCombat = Instant.now().plusSeconds(time);
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public Player getEnemy() {
        return Bukkit.getPlayer(this.enemyUniqueId);
    }

    public Instant getEndOfCombat() {
        return endOfCombat;
    }

}
