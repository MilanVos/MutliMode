package nl.multitime.mutliMode.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import nl.multitime.mutliMode.MutliMode;

import java.util.Arrays;

public class ClassSelectionMenu {

    private final MutliMode plugin;
    private final Player player;

    public ClassSelectionMenu(MutliMode plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void open() {
        Inventory inventory = Bukkit.createInventory(null, 27, "§6Kies je Klasse");

        ItemStack warriorItem = new ItemStack(Material.IRON_SWORD);
        ItemMeta warriorMeta = warriorItem.getItemMeta();
        warriorMeta.setDisplayName("§cWarrior");
        warriorMeta.setLore(Arrays.asList(
                "§7Een sterke krijger met",
                "§7verhoogde kracht en bescherming.",
                "",
                "§eKlik om te selecteren!"
        ));
        warriorItem.setItemMeta(warriorMeta);
        inventory.setItem(10, warriorItem);

        ItemStack archerItem = new ItemStack(Material.BOW);
        ItemMeta archerMeta = archerItem.getItemMeta();
        archerMeta.setDisplayName("§aArcher");
        archerMeta.setLore(Arrays.asList(
                "§7Een snelle boogschutter met",
                "§7verhoogde snelheid.",
                "",
                "§eKlik om te selecteren!"
        ));
        archerItem.setItemMeta(archerMeta);
        inventory.setItem(12, archerItem);

        ItemStack mageItem = new ItemStack(Material.BLAZE_ROD);
        ItemMeta mageMeta = mageItem.getItemMeta();
        mageMeta.setDisplayName("§9Mage");
        mageMeta.setLore(Arrays.asList(
                "§7Een magische tovenaar met",
                "§7speciale krachten.",
                "",
                "§eKlik om te selecteren!"
        ));
        mageItem.setItemMeta(mageMeta);
        inventory.setItem(14, mageItem);

        ItemStack assassinItem = new ItemStack(Material.ENDER_PEARL);
        ItemMeta assassinMeta = assassinItem.getItemMeta();
        assassinMeta.setDisplayName("§8Assassin");
        assassinMeta.setLore(Arrays.asList(
                "§7Een sluwe sluipmoordenaar met",
                "§7verhoogde snelheid en springkracht.",
                "",
                "§eKlik om te selecteren!"
        ));
        assassinItem.setItemMeta(assassinMeta);
        inventory.setItem(16, assassinItem);

        player.openInventory(inventory);
    }
}
