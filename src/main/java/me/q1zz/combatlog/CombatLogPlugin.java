package me.q1zz.combatlog;

import com.google.common.base.Stopwatch;
import com.sk89q.worldguard.WorldGuard;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.q1zz.combatlog.bStats.Metrics;
import me.q1zz.combatlog.combat.Combat;
import me.q1zz.combatlog.combat.CombatManager;
import me.q1zz.combatlog.configuration.PluginConfiguration;
import me.q1zz.combatlog.listener.*;
import me.q1zz.combatlog.task.CombatLogTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CombatLogPlugin extends JavaPlugin {

    private PluginConfiguration pluginConfiguration;
    private CombatManager combatManager;
    private WorldGuard worldGuard;

    private static final int PLUGIN_ID = 17431;

    @Override
    public void onEnable() {
        this.getLogger().info("Starting...");

        final Stopwatch stopwatch = Stopwatch.createStarted();

        this.initConfigs();

        this.combatManager = new CombatManager();

        this.setupWorldGuard();

        this.runTasks();
        this.registerListeners();

        new Metrics(this, PLUGIN_ID);

        this.getLogger().info("Successfully started in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms!");
    }

    @Override
    public void onDisable() {
        for (Combat combat : this.combatManager.getCombats()) {
            this.combatManager.removeCombat(combat.getUniqueId());
        }
    }

    private void setupWorldGuard() {

        if(this.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
            this.worldGuard = WorldGuard.getInstance();
            return;
        }

        this.getLogger().warning("The WorldGuard plugin has not been found! Some functions may be disabled.");

    }

    private void registerListeners() {
        Stream.of(
                new BlockPlaceListener(this.combatManager, this.pluginConfiguration),
                new EntityDamageByEntityListener(this.combatManager, this.pluginConfiguration),
                new PlayerCommandPreprocessListener(this.combatManager, this.pluginConfiguration),
                new PlayerQuitListener(this.combatManager, this.pluginConfiguration),
                new PlayerDeathListener(this.combatManager, this.pluginConfiguration),
                new InventoryOpenListener(this.combatManager, this.pluginConfiguration),
                new PlayerMoveListener(this.combatManager, this.pluginConfiguration, this.worldGuard)
        ).forEach(event -> Bukkit.getPluginManager().registerEvents(event, this));
    }

    private void runTasks() {
        final CombatLogTask combatTask = new CombatLogTask(this.combatManager, this.pluginConfiguration);
        if (this.pluginConfiguration.getSettings().isAsync()) {
            combatTask.runTaskTimerAsynchronously(this, 20L, 20L);
        } else
            combatTask.runTaskTimer(this, 20L, 20L);
    }

    private void initConfigs() {
        this.pluginConfiguration = ConfigManager.create(PluginConfiguration.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer());
            it.withBindFile(new File(this.getDataFolder(), "configuration.yml"));
            it.saveDefaults();
            it.withRemoveOrphans(true);
            it.load(true);
        });
    }

}
