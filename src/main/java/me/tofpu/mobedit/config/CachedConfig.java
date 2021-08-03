package me.tofpu.mobedit.config;

import me.tofpu.mobedit.mob.CustomMob;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CachedConfig {
    private final static String MAIN_PATH = "edit";

    private final List<CustomMob> customMobs;
    private FileConfiguration configuration;

    public CachedConfig(final FileConfiguration configuration){
        this.configuration = configuration;
        this.customMobs = new ArrayList<>();
    }

    public void reload(final FileConfiguration configuration){
        this.configuration = configuration;
        this.customMobs.clear();
        initialize();
    }

    public void initialize(){
        final ConfigurationSection section = configuration.getConfigurationSection(MAIN_PATH);

        for (final String key : section.getKeys(false)){
            EntityType type;
            try {
                type = EntityType.valueOf(key.toUpperCase());
            } catch (IllegalArgumentException ex){
                continue;
            }

            if (!isMob(type)){
                continue;
            }
            final CustomMob customMob = new CustomMob(type);

            final ConfigurationSection armor = section.getConfigurationSection(key + ".armor");
            final String heldItem = section.getString(key + ".held-item", null);

            if (heldItem != null) {
                final Material heldItemMaterial = Material.matchMaterial(heldItem);
                if (heldItemMaterial != null && heldItemMaterial.isItem()) {
                    customMob.setHeldItem(new ItemStack(heldItemMaterial, 1));
                }
            }

            if (armor != null){
                final String[] armorArray = new String[]{"boots", "leggings", "chestplate", "helmet"};

                for (int i = armorArray.length - 1; i >= 0; --i){
                    final String output = armor.getString(armorArray[i], null);
                    if (output == null) continue;

                    final Material material = Material.matchMaterial(output);
                    if (material == null || !isArmor(material)) continue;
                    customMob.getArmors()[i] = new ItemStack(material, 1);
                }
            }

            this.customMobs.add(customMob);
        }
    }

    public static boolean isMob(final EntityType entityType){
        final Class<?> interface0 = entityType.getEntityClass().getInterfaces()[0];
        final Class<?> interface1 = interface0.getInterfaces()[0];

        final String monster = "monster";
        return interface0.getSimpleName().equalsIgnoreCase(monster) || interface1.getSimpleName().equalsIgnoreCase(monster);
    }

    private boolean isArmor(final Material material){
        return material.name().contains("HELMET") || material.name().contains("CHESTPLATE")
                || material.name().contains("LEGGINGS") || material.name().contains("BOOTS");
    }

    public List<CustomMob> getCustomMobs() {
        return customMobs;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }
}
