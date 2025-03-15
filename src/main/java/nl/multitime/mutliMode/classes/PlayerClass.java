package nl.multitime.mutliMode.classes;

import org.bukkit.entity.Player;

public abstract class PlayerClass {

    protected final Player player;
    private final String className;

    public PlayerClass(Player player, String className) {
        this.player = player;
        this.className = className;
    }

    public abstract void applyAbilities();

    public abstract void removeAbilities();

    protected abstract void clearClassItems();

    public String getClassName() {
        return className;
    }

    public Player getPlayer() {
        return player;
    }
}