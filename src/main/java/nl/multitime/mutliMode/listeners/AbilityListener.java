package nl.multitime.mutliMode.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.classes.ArcherClass;
import nl.multitime.mutliMode.classes.AssassinClass;
import nl.multitime.mutliMode.classes.MageClass;
import nl.multitime.mutliMode.classes.PlayerClass;
import nl.multitime.mutliMode.classes.WarriorClass;

public class AbilityListener implements Listener {

    private final MutliMode plugin;

    public AbilityListener(MutliMode plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getClassManager().hasClass(player)) {
            return;
        }

        PlayerClass playerClass = plugin.getClassManager().getPlayerClass(player);
        ItemStack item = event.getItem();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item == null) return;

            if (playerClass instanceof WarriorClass && item.getType() == Material.IRON_SWORD) {
                if (player.isSneaking()) {
                    plugin.getAbilityManager().activateAbility(player, "Berserker Rage");
                    event.setCancelled(true);
                }
            }

            else if (playerClass instanceof ArcherClass && item.getType() == Material.BOW) {
                if (player.isSneaking()) {
                    plugin.getAbilityManager().activateAbility(player, "Precision Shot");
                }
            }

            else if (playerClass instanceof MageClass && item.getType() == Material.BLAZE_ROD) {
                plugin.getAbilityManager().activateAbility(player, "Freeze");
                event.setCancelled(true);
            }

            else if (playerClass instanceof AssassinClass && item.getType() == Material.ENDER_PEARL) {
                if (player.isSneaking()) {
                    if (plugin.getAbilityManager().activateAbility(player, "Stealth")) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.hasMetadata("precision_shot")) {

                if (event.getEntity() instanceof Player) {
                    Player victim = (Player) event.getEntity();
                    victim.sendMessage("§cJe bent geraakt door een Precision Shot!");
                }
            }
        }

        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();

            if (plugin.getClassManager().hasClass(attacker) &&
                    plugin.getClassManager().getPlayerClass(attacker) instanceof AssassinClass) {

                if (attacker.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    event.setDamage(event.getDamage() * 2.0);

                    attacker.sendMessage("§8§lBACKSTAB! §7Je deed dubbele schade!");

                    if (event.getEntity() instanceof Player) {
                        Player victim = (Player) event.getEntity();
                        victim.sendMessage("§c§lJe bent backstabbed door een Assassin!");
                    }
                }
            }
        }
    }
}
