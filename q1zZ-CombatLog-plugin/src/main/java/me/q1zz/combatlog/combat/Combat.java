package me.q1zz.combatlog.combat;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Combat {

    private final UUID playerId;
    private Instant endOfCombat;

    public boolean isExpired() {
        return Instant.now().isAfter(this.endOfCombat);
    }

    @NotNull
    public Duration getRemainingTime() {

        final Duration remainingTime = Duration.between(Instant.now(), this.endOfCombat);

        if(remainingTime.isZero() || remainingTime.isNegative()) {
            return Duration.ZERO;
        }

        return remainingTime;
    }

}
