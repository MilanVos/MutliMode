package nl.multitime.mutliMode.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import nl.multitime.mutliMode.MutliMode;

public class MenuListener implements Listener {

    private final MutliMode plugin;

    public MenuListener(MutliMode plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6Kies je Klasse")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
                return;
            }

            Player player = (Player) event.getWhoClicked();

            if (plugin.getClassManager().hasClass(player) &&
                    !plugin.getConfig().getBoolean("settings.allow-class-change", true)) {
                player.sendMessage("§cJe kunt je klasse niet meer veranderen!");
                player.closeInventory();
                return;
            }

            if (event.getSlot() == 10) {
                plugin.getClassManager().setPlayerClass(player, "warrior");
                player.closeInventory();
            } else if (event.getSlot() == 12) {
                plugin.getClassManager().setPlayerClass(player, "archer");
                player.closeInventory();
            } else if (event.getSlot() == 14) {
                plugin.getClassManager().setPlayerClass(player, "mage");
                player.closeInventory();
            } else if (event.getSlot() == 16) {
                plugin.getClassManager().setPlayerClass(player, "assassin");
                player.closeInventory();
            }
        }
    }
}
