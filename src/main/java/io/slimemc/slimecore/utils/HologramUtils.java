package io.slimemc.slimecore.utils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;

public class HologramUtils {
    private List<String> lines;
    private final List<ArmorStand> armorStands = new ArrayList<>();
    private Location location;
    private float gap = 0.25F;

    public HologramUtils(String... lines) {
        this.lines = new ArrayList<>(List.of(lines));
    }

    public HologramUtils place(Location location) {
        armorStands.clear();
        this.location = location;
        float yOffset = 0;
        for (String line : lines) {
            Location loc = location.clone();
            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(loc.add(0, yOffset, 0), EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomName(TextUtils.formatText(line));
            armorStand.setCustomNameVisible(true);
            armorStand.setMarker(true);
            armorStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
            armorStands.add(armorStand);
            yOffset -= gap;
        }
        return this;
    }

    public HologramUtils remove() {
        this.location = null;
        for (ArmorStand armorStand : armorStands) {
            armorStand.remove();
        }
        armorStands.clear();
        return this;
    }

    public HologramUtils setLines(String... lines) {
        this.lines = List.of(lines);
        replace();
        return this;
    }

    public HologramUtils addLines(String... lines) {
        this.lines.addAll(List.of(lines));
        replace();
        return this;
    }

    public HologramUtils removeLines(int... lines) {
        for (int line : lines) {
            if (this.lines.size() >= line) {
                this.lines.remove(line);
            }
        }
        replace();
        return this;
    }

    public HologramUtils setGap(float gap) {
        this.gap = gap;
        replace();
        return this;
    }

    public float getGap() {
        return gap;
    }

    public HologramUtils setLocation(Location location) {
        replace();
        return this;
    }

    public Location getLocation() {
        return location;
    }

    private void replace() {
        if (armorStands.size() > 0) {
            for (ArmorStand armorStand : armorStands) {
                armorStand.remove();
            }
            place(location);
        }
    }
}
