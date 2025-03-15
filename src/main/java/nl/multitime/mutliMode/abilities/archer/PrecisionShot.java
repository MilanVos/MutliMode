package nl.multitime.mutliMode.abilities.archer;

import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import nl.multitime.mutliMode.MutliMode;
import nl.multitime.mutliMode.abilities.Ability;

public class PrecisionShot extends Ability {

    private final MutliMode plugin;

    public PrecisionShot(Player player, MutliMode plugin) {
        super(player, "Precision Shot", 30); // 30 seconds cooldown
        this.plugin = plugin;
    }

    @Override
    public boolean activate() {
        player.sendMessage("§a§lPRECISION SHOT READY!");
        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 2.0f);

        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setVelocity(arrow.getVelocity().multiply(2.0));
        arrow.setCritical(true);
        arrow.setDamage(arrow.getDamage() * 3.0);
        arrow.setMetadata("precision_shot", new FixedMetadataValue(plugin, true));

        return true;
    }
}
