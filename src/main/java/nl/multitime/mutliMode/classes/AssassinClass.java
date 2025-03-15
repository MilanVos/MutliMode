package nl.multitime.mutliMode.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.assassin.Stealth;

public class AssassinClass extends PlayerClass {

    private final MutliMode plugin;

    public AssassinClass(Player player, MutliMode plugin) {
        super(player, "Assassin");
        this.plugin = plugin;
    }

    @Override
    public void applyAbilities() {
        clearClassItems();

        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 16));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1, false, false));

        plugin.getAbilityManager().registerAbility(player, new Stealth(player, plugin));

        player.sendMessage("§8§lASSASSIN CLASS");
        player.sendMessage("§7Je bent een sluwe sluipmoordenaar met verhoogde snelheid en springkracht.");
        player.sendMessage("§7Speciale ability: §8Stealth §7(Shift + Rechtermuisknop met ender pearl)");
    }

    @Override
    public void removeAbilities() {
        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.JUMP);

        plugin.getAbilityManager().unregisterAllAbilities(player);

        clearClassItems();
    }
    @Override
    protected void clearClassItems() {
        player.getInventory().remove(Material.IRON_SWORD);
        player.getInventory().removeItem(new ItemStack(Material.ENDER_PEARL, 16));
    }

}
