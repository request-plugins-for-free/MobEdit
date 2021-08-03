package me.tofpu.mobedit.listener;

import me.tofpu.mobedit.MobEdit;
import me.tofpu.mobedit.config.CachedConfig;
import me.tofpu.mobedit.mob.CustomMob;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;

public class CreatureSpawnListener implements Listener {
    private final CachedConfig cachedConfig;

    public CreatureSpawnListener(final CachedConfig cachedConfig) {
        this.cachedConfig = cachedConfig;
    }

    @EventHandler(ignoreCancelled = true)
    private void onCreatureSpawn(final CreatureSpawnEvent event){
        if (!(event.getEntity() instanceof Monster)) return;
        Monster entity = (Monster) event.getEntity();

        for (CustomMob customMob : this.cachedConfig.getCustomMobs()){
            if (customMob.getType() != event.getEntityType()) continue;
            final EntityEquipment equipment = entity.getEquipment();
            if (equipment == null) continue;

            if (customMob.getHeldItem() != null) {
                equipment.setItemInMainHand(customMob.getHeldItem());
            }
            equipment.setArmorContents(customMob.getArmors());
            break;
        }
    }
}
