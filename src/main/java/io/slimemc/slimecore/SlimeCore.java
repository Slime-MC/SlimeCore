package io.slimemc.slimecore;

import io.slimemc.slimecore.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SlimeCore extends JavaPlugin {

    private final String coreVersion = "1.0.1";
    private final int coreVersionAsInt = 9;

    private boolean error = false;

    public abstract void onPluginLoad();
    public abstract void onPluginEnable();
    public abstract void onPluginDisable();

    @Override
    public void onLoad() {
        try {
            onPluginLoad();
        } catch (Throwable throwable) {
            Bukkit.getLogger().severe("An error occurred trying to load " + getDescription().getName()
                    + ". Disabling the plugin! Error:" + throwable.getMessage());
            error = true;
        }
    }

    @Override
    public void onEnable() {
        if (error) {
            setEnabled(false);
            return;
        }

        try {
            onPluginEnable();
            sendMessage("&8=============================");
            sendMessage("&a" + getDescription().getName() + " &ev" + getDescription().getVersion());
            sendMessage("&7Core: &ev" + coreVersion + "(" + coreVersionAsInt + ")");
            sendMessage("&7Action: &aEnabling...");
        } catch (Throwable throwable) {
            Bukkit.getLogger().severe("An error occurred trying to enable " + getDescription().getName()
                    + ". Disabling the plugin! Error:" + throwable.getMessage());
            error = true;
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        sendMessage("&8=============================");

    }

    @Override
    public void onDisable() {
        if (error) {
            return;
        }

        try {
            onPluginDisable();
            sendMessage("&8=============================");
            sendMessage("&a" + getDescription().getName() + " &ev" + getDescription().getVersion());
            sendMessage("&7Core: &ev" + coreVersion + "(" + coreVersionAsInt + ")");
            sendMessage("&7Action: &cDisabling...");
        } catch (Throwable throwable) {
            Bukkit.getLogger().severe("An error occurred trying to disable " + getDescription().getName()
                    + ". Disabling the plugin! Error:" + throwable.getMessage());
        }
        sendMessage("&8=============================");
    }

    private void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(TextUtils.formatText(message));
    }

}
