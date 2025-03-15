package nl.multitime.mutliMode.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.classes.PlayerClass;
import nl.multitime.mutliMode.classes.WarriorClass;
import nl.multitime.mutliMode.classes.ArcherClass;
import nl.multitime.mutliMode.classes.AssassinClass;
import nl.multitime.mutliMode.classes.MageClass;

public class ClassManager {

    private final MutliMode plugin;
    private final Map<UUID, PlayerClass> playerClasses;

    public ClassManager(MutliMode plugin) {
        this.plugin = plugin;
        this.playerClasses = new HashMap<>();
    }

    public void setPlayerClass(Player player, String className) {
        PlayerClass playerClass;

        switch (className.toLowerCase()) {
            case "warrior":
                playerClass = new WarriorClass(player, plugin);
                break;
            case "archer":
                playerClass = new ArcherClass(player, plugin);
                break;
            case "mage":
                playerClass = new MageClass(player, plugin);
                break;
            case "assassin":
                playerClass = new AssassinClass(player, plugin);
                break;
            default:
                return;
        }

        if (playerClasses.containsKey(player.getUniqueId())) {
            playerClasses.get(player.getUniqueId()).removeAbilities();
        }

        playerClasses.put(player.getUniqueId(), playerClass);
        playerClass.applyAbilities();

        plugin.getDataManager().savePlayerClass(player, className.toLowerCase());

        player.sendMessage("§aJe hebt de " + className + " klasse gekozen!");
    }

    public PlayerClass getPlayerClass(Player player) {
        return playerClasses.get(player.getUniqueId());
    }

    public boolean hasClass(Player player) {
        return playerClasses.containsKey(player.getUniqueId());
    }

    public void loadPlayerClass(Player player) {
        String className = plugin.getDataManager().getPlayerClass(player);

        if (className != null) {
            setPlayerClass(player, className);
        }
    }
}
