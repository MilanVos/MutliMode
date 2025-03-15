package nl.multitime.mutliMode.abilities.warrior;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.Ability;

public class BerserkerRage extends Ability {

    private final MutliMode plugin;

    public BerserkerRage(Player player, MutliMode plugin) {
        super(player, "Berserker Rage", 60); // 60 seconds cooldown
        this.plugin = plugin;
    }

    @Override
    public boolean activate() {
        player.sendMessage("§c§lBERSERKER RAGE ACTIVATED!");
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0, false, true));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("§cBerserker Rage has worn off.");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 0.5f);
            }
        }.runTaskLater(plugin, 200L); // 200 ticks = 10 seconds

        return true;
    }
}
