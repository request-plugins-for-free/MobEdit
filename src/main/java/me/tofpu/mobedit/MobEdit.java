package me.tofpu.mobedit;

import me.tofpu.mobedit.command.MobEditCommand;
import me.tofpu.mobedit.config.CachedConfig;
import me.tofpu.mobedit.listener.CreatureSpawnListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobEdit extends JavaPlugin {
    private CachedConfig cachedConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        this.cachedConfig = new CachedConfig(this.getConfig());
        this.cachedConfig.initialize();

        getCommand("mobedit").setExecutor(new MobEditCommand(this));

        getServer().getPluginManager().registerEvents(new CreatureSpawnListener(this.cachedConfig), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reload(){
        reloadConfig();
        cachedConfig.reload(this.getConfig());
    }

    public CachedConfig getCachedConfig() {
        return cachedConfig;
    }
}
