package nl.multitime.mutliMode.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.classes.AssassinClass;

public class StealthListener implements Listener {

    private final MutliMode plugin;

    public StealthListener(MutliMode plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (plugin.getClassManager().hasClass(player) &&
            plugin.getClassManager().getPlayerClass(player) instanceof AssassinClass) {

            if (event.isSneaking()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0, false, false));
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (plugin.getClassManager().hasClass(player) &&
                plugin.getClassManager().getPlayerClass(player) instanceof AssassinClass) {

                if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    player.sendMessage("§c§lJe Stealth is verbroken omdat je schade hebt gekregen!");
                }
            }
        }
    }
}
