package nl.multitime.mutliMode.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.Ability;

public class AbilityManager {

    private final MutliMode plugin;
    private final Map<UUID, Map<String, Ability>> playerAbilities;
    private final Map<UUID, Map<String, BukkitTask>> abilityCooldowns;

    public AbilityManager(MutliMode plugin) {
        this.plugin = plugin;
        this.playerAbilities = new HashMap<>();
        this.abilityCooldowns = new HashMap<>();
    }

    public void registerAbility(Player player, Ability ability) {
        UUID playerId = player.getUniqueId();

        if (!playerAbilities.containsKey(playerId)) {
            playerAbilities.put(playerId, new HashMap<>());
        }

        playerAbilities.get(playerId).put(ability.getName(), ability);
    }

    public void unregisterAbility(Player player, String abilityName) {
        UUID playerId = player.getUniqueId();

        if (playerAbilities.containsKey(playerId)) {
            playerAbilities.get(playerId).remove(abilityName);
        }
    }

    public void unregisterAllAbilities(Player player) {
        UUID playerId = player.getUniqueId();
        playerAbilities.remove(playerId);
    }

    public boolean activateAbility(Player player, String abilityName) {
        UUID playerId = player.getUniqueId();

        if (!playerAbilities.containsKey(playerId) ||
            !playerAbilities.get(playerId).containsKey(abilityName)) {
            return false;
        }

        if (abilityCooldowns.containsKey(playerId) &&
            abilityCooldowns.get(playerId).containsKey(abilityName)) {
            player.sendMessage("§cDeze ability is nog in cooldown!");
            return false;
        }

        Ability ability = playerAbilities.get(playerId).get(abilityName);

        boolean success = ability.activate();

        if (success && ability.getCooldown() > 0) {
            if (!abilityCooldowns.containsKey(playerId)) {
                abilityCooldowns.put(playerId, new HashMap<>());
            }

            BukkitTask cooldownTask = new BukkitRunnable() {
                @Override
                public void run() {
                    if (abilityCooldowns.containsKey(playerId) &&
                        abilityCooldowns.get(playerId).containsKey(abilityName)) {
                        abilityCooldowns.get(playerId).remove(abilityName);
                        if (player.isOnline()) {
                            player.sendMessage("§a" + ability.getName() + " is nu weer beschikbaar!");
                        }
                    }
                }
            }.runTaskLater(plugin, ability.getCooldown() * 20L);

            abilityCooldowns.get(playerId).put(abilityName, cooldownTask);
        }

        return success;
    }
}
