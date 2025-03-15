package nl.multitime.mutliMode.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.ui.ClassSelectionMenu;

public class ClassCommand implements CommandExecutor {

    private final MutliMode plugin;

    public ClassCommand(MutliMode plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cAlleen spelers kunnen dit commando gebruiken!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            new ClassSelectionMenu(plugin, player).open();
            return true;
        }

        if (args.length == 1) {
            String className = args[0];

            if (className.equalsIgnoreCase("warrior") ||
                className.equalsIgnoreCase("archer") ||
                className.equalsIgnoreCase("mage") ||
                className.equalsIgnoreCase("assassin")) {

                if (plugin.getConfig().getBoolean("settings.allow-class-change", true) ||
                    !plugin.getClassManager().hasClass(player)) {
                    plugin.getClassManager().setPlayerClass(player, className);
                } else {
                    player.sendMessage("§cJe kunt je klasse niet meer veranderen!");
                }
                return true;
            } else {
                player.sendMessage("§cOnbekende klasse! Kies uit: Warrior, Archer, Mage, Assassin");
                return true;
            }
        }

        return false;
    }
}