package me.q1zz.combatlog.combat;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CombatManager {

    private final Map<UUID, Combat> combatMap = new ConcurrentHashMap<>();

    @NotNull
    public Collection<Combat> getCombats() {
        return Collections.unmodifiableCollection(this.combatMap.values());
    }

    @Nullable
    public Combat getCombat(@NotNull UUID playerId) {
        return this.combatMap.get(playerId);
    }

    public boolean hasCombat(@NotNull UUID playerId) {

        final Combat combat = this.combatMap.get(playerId);

        if(combat == null) {
            return false;
        }

        return !combat.isExpired();
    }

    public void addCombat(@NotNull UUID playerId, @NotNull Duration time) {

        final Instant endOfCombat = Instant.now().plus(time);
        final Combat combat = new Combat(playerId, endOfCombat);

        this.combatMap.put(playerId, combat);
    }

    public void removeCombat(@NotNull UUID playerId) {
        this.combatMap.remove(playerId);
    }

    public void removeAll() {
        this.combatMap.clear();
    }

}
