package nl.multitime.mutliMode.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.mage.Freeze;

public class MageClass extends PlayerClass {

    private final MutliMode plugin;

    public MageClass(Player player, MutliMode plugin) {
        super(player, "Mage");
        this.plugin = plugin;
    }

    @Override
    public void applyAbilities() {
        clearClassItems();

        player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
        player.getInventory().addItem(new ItemStack(Material.BOOK));

        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));

        plugin.getAbilityManager().registerAbility(player, new Freeze(player, plugin));

        player.sendMessage("§9§lMAGE CLASS");
        player.sendMessage("§7Je bent een magische tovenaar met speciale krachten.");
        player.sendMessage("§7Speciale ability: §b§lFreeze §7(Rechtermuisknop met blaze rod)");
    }

    @Override
    public void removeAbilities() {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);

        plugin.getAbilityManager().unregisterAllAbilities(player);

        clearClassItems();
    }

    @Override
    protected void clearClassItems() {
        player.getInventory().remove(Material.BLAZE_ROD);
        player.getInventory().remove(Material.BOOK);
    }

}
