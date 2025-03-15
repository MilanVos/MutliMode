package nl.multitime.mutliMode.abilities;

import org.bukkit.entity.Player;

public abstract class Ability {

    protected final Player player;
    protected final String name;
    protected final int cooldown; // in seconds

    public Ability(Player player, String name, int cooldown) {
        this.player = player;
        this.name = name;
        this.cooldown = cooldown;
    }

    /**
     * Activates the ability
     * @return true if the ability was activated successfully
     */
    public abstract boolean activate();

    public String getName() {
        return name;
    }

    public int getCooldown() {
        return cooldown;
    }
}
