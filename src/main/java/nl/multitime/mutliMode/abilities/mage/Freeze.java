package nl.multitime.mutliMode.abilities.mage;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.Ability;

public class Freeze extends Ability {

    private final MutliMode plugin;

    public Freeze(Player player, MutliMode plugin) {
        super(player, "Freeze", 15); // 15 seconden cooldown
        this.plugin = plugin;
    }

    @Override
    public boolean activate() {
        player.sendMessage("§b§lFREEZE!");
        player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);

        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;

                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 100, false, true));
                target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 40, 128, false, true)); // Voorkomt springen

                target.getWorld().spawnParticle(Particle.SNOWBALL, target.getLocation().add(0, 1, 0), 30, 0.5, 1, 0.5, 0.1);
                target.getWorld().spawnParticle(Particle.SNOW_SHOVEL, target.getLocation().add(0, 0.5, 0), 20, 0.3, 0.5, 0.3, 0.05);

                new BukkitRunnable() {
                    int ticks = 0;

                    @Override
                    public void run() {
                        if (ticks >= 40 || !target.isValid()) {
                            cancel();
                            return;
                        }

                        Location loc = target.getLocation().add(0, 1, 0);
                        target.getWorld().spawnParticle(Particle.SNOW_SHOVEL, loc, 5, 0.4, 0.5, 0.4, 0);

                        double radius = 0.8;
                        for (int i = 0; i < 8; i++) {
                            double angle = 2 * Math.PI * i / 8;
                            double x = radius * Math.cos(angle);
                            double z = radius * Math.sin(angle);
                            Location particleLoc = loc.clone().add(x, 0, z);

                            target.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1,
                                    new Particle.DustOptions(Color.fromRGB(173, 216, 230), 1.0f)); // Lichtblauw
                        }

                        ticks += 5;
                    }
                }.runTaskTimer(plugin, 0L, 5L);

                if (target instanceof Player) {
                    Player targetPlayer = (Player) target;
                    targetPlayer.sendMessage("§b§lJe bent bevroren door een Mage!");

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            targetPlayer.sendMessage("§aJe kunt weer bewegen!");
                            target.getWorld().spawnParticle(Particle.WATER_SPLASH, target.getLocation().add(0, 1, 0), 40, 0.5, 1, 0.5, 0.2);
                        }
                    }.runTaskLater(plugin, 40L);
                }
            }
        }

        return true;
    }
}