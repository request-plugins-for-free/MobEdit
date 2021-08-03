package me.tofpu.mobedit.mob;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CustomMob {
    private final EntityType type;
    private final ItemStack[] armors;

    private ItemStack heldItem;

    public CustomMob(EntityType type) {
        this(type, new ItemStack[4], null);
    }

    public CustomMob(EntityType type, ItemStack[] armors, ItemStack heldItem) {
        this.type = type;
        this.armors = new ItemStack[4];
        System.arraycopy(armors, 0, this.armors, 0, armors.length);

        this.heldItem = heldItem;
    }

    public void setHeldItem(ItemStack heldItem) {
        this.heldItem = heldItem;
    }

    public EntityType getType() {
        return type;
    }

    public ItemStack[] getArmors() {
        return armors;
    }

    public ItemStack getHeldItem() {
        return heldItem;
    }

    @Override
    public String toString() {
        return "CustomMob{" +
                "type=" + type +
                ", armors=" + Arrays.toString(armors) +
                ", heldItem=" + heldItem +
                '}';
    }
}
