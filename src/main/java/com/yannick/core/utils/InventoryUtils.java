package com.yannick.core.utils;

import org.bukkit.event.block.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
    public static int getActionValue(Action action) {
        int actionValue = 0;
        switch (action) {
            case LEFT_CLICK_BLOCK:
                actionValue = 0;
            case LEFT_CLICK_AIR:
                actionValue = 0;
            case RIGHT_CLICK_BLOCK:
                actionValue = 1;
            case RIGHT_CLICK_AIR:
                actionValue = 1;
            case PHYSICAL:
                actionValue = 2;
        }
        return actionValue;

    }

    public static int getInventoryCount(Inventory inventory) {
        return getInventoryCount(inventory, false);
    }

    public static int getInventoryCount(Inventory inventory, boolean includeStackSize) {
        int count = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                count += includeStackSize ? item.getAmount() : 1;
            }
        }
        return count;
    }
}
