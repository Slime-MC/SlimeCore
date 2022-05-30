package com.yannick.core.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Config {
    private final File file;
    private final YamlConfiguration config;

    public Config(Plugin plugin, String configName) {
        file = new File(plugin.getDataFolder(), configName);

        if (!file.exists()) {
            file.getParentFile().mkdir();
            plugin.saveResource(configName, true);
        }

        config = new YamlConfiguration();

        try {
            config.load(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }
    public YamlConfiguration getConfig() {
        return config;
    }
}