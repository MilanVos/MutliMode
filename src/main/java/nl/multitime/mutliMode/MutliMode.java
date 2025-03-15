package nl.multitime.mutliMode;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import nl.multitime.mutliMode.commands.ClassCommand;
import nl.multitime.mutliMode.listeners.AbilityListener;
import nl.multitime.mutliMode.listeners.MenuListener;
import nl.multitime.mutliMode.listeners.PlayerJoinListener;
import nl.multitime.mutliMode.listeners.StealthListener;
import nl.multitime.mutliMode.managers.AbilityManager;
import nl.multitime.mutliMode.managers.ClassManager;
import nl.multitime.mutliMode.managers.DataManager;

public final class MutliMode extends JavaPlugin {

    private ClassManager classManager;
    private AbilityManager abilityManager;
    private DataManager dataManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        dataManager = new DataManager(this);
        abilityManager = new AbilityManager(this);
        classManager = new ClassManager(this);

        getCommand("class").setExecutor(new ClassCommand(this));

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);
        Bukkit.getPluginManager().registerEvents(new AbilityListener(this), this);
        Bukkit.getPluginManager().registerEvents(new StealthListener(this), this);

        getLogger().info("MutliMode survival class plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MutliMode survival class plugin has been disabled!");
    }

    public ClassManager getClassManager() {
        return classManager;
    }

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
