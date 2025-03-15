package nl.multitime.mutliMode.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.warrior.BerserkerRage;

public class WarriorClass extends PlayerClass {

    private final MutliMode plugin;

    public WarriorClass(Player player, MutliMode plugin) {
        super(player, "Warrior");
        this.plugin = plugin;
    }

    @Override
    public void applyAbilities() {
        clearClassItems();

        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().addItem(new ItemStack(Material.SHIELD));

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));

        plugin.getAbilityManager().registerAbility(player, new BerserkerRage(player, plugin));

        player.sendMessage("§c§lWARRIOR CLASS");
        player.sendMessage("§7Je bent een sterke krijger met verhoogde kracht en bescherming.");
        player.sendMessage("§7Speciale ability: §cBerserker Rage §7(Shift + Rechtermuisknop met zwaard)");
    }

    @Override
    public void removeAbilities() {
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

        plugin.getAbilityManager().unregisterAllAbilities(player);

        clearClassItems();
    }

    @Override
    protected void clearClassItems() {
        player.getInventory().remove(Material.IRON_SWORD);
        player.getInventory().remove(Material.SHIELD);
    }

}