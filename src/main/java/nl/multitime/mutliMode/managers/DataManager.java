package nl.multitime.mutliMode.managers;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import nl.multitime.mutliMode.MutliMode;

public class DataManager {

    private final MutliMode plugin;
    private final File dataFile;
    private FileConfiguration dataConfig;

    public DataManager(MutliMode plugin) {
        this.plugin = plugin;
        this.dataFile = new File(plugin.getDataFolder(), "playerdata.yml");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create playerdata.yml!");
                e.printStackTrace();
            }
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void savePlayerClass(Player player, String className) {
        dataConfig.set("players." + player.getUniqueId().toString() + ".class", className);
        saveData();
    }

    public String getPlayerClass(Player player) {
        return dataConfig.getString("players." + player.getUniqueId().toString() + ".class");
    }

    public void saveData() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save playerdata.yml!");
            e.printStackTrace();
        }
    }

    public void reloadData() {
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }
}
