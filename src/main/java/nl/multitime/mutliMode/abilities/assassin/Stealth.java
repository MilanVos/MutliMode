package nl.multitime.mutliMode.abilities.assassin;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.Ability;

public class Stealth extends Ability {

    private final MutliMode plugin;

    public Stealth(Player player, MutliMode plugin) {
        super(player, "Stealth", 45); // 45 seconden cooldown
        this.plugin = plugin;
    }

    @Override
    public boolean activate() {
        player.sendMessage("§8§lSTEALTH ACTIVATED!");
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0, false, false));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2, false, false));
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("§8Stealth verloopt over 3 seconden...");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.5f);
            }
        }.runTaskLater(plugin, 140L);

        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("§cStealth is verlopen!");
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.5f);
            }
        }.runTaskLater(plugin, 200L);

        return true;
    }
}
