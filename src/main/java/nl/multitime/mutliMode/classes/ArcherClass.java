package nl.multitime.mutliMode.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.archer.PrecisionShot;

public class ArcherClass extends PlayerClass {

    private final MutliMode plugin;

    public ArcherClass(Player player, MutliMode plugin) {
        super(player, "Archer");
        this.plugin = plugin;
    }

    @Override
    public void applyAbilities() {
        clearClassItems();

        player.getInventory().addItem(new ItemStack(Material.BOW));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 64));

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));

        plugin.getAbilityManager().registerAbility(player, new PrecisionShot(player, plugin));

        player.sendMessage("§a§lARCHER CLASS");
        player.sendMessage("§7Je bent een snelle boogschutter met verhoogde snelheid.");
        player.sendMessage("§7Speciale ability: §aPrecision Shot §7(Shift + Rechtermuisknop met boog)");
    }

    @Override
    public void removeAbilities() {
        player.removePotionEffect(PotionEffectType.SPEED);

        plugin.getAbilityManager().unregisterAllAbilities(player);

        clearClassItems();
    }
    @Override
    protected void clearClassItems() {
        player.getInventory().remove(Material.BOW);
        player.getInventory().removeItem(new ItemStack(Material.ARROW, 64)); // Verwijder pijlen
    }

}