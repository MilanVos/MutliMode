package nl.multitime.mutliMode.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.ui.ClassSelectionMenu;

public class PlayerJoinListener implements Listener {

    private final MutliMode plugin;

    public PlayerJoinListener(MutliMode plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String savedClass = plugin.getDataManager().getPlayerClass(player);

        if (savedClass != null) {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                plugin.getClassManager().setPlayerClass(player, savedClass);
                player.sendMessage("§6Welkom terug! §eJe speelt als een " + savedClass + ".");
            }, 10L);
        } else {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                new ClassSelectionMenu(plugin, player).open();
                player.sendMessage("§6Welkom bij de survival! §eKies een klasse om te beginnen.");
            }, 20L);
        }
    }
}
