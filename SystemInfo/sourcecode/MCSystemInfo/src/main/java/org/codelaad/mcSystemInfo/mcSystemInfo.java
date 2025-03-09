package org.codelaad.mcSystemInfo;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class mcSystemInfo extends JavaPlugin {
    private File configFile;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("MCSysInfo Plugin has been enabled!");

        // Load the configuration file
        createConfig();

        // Register commands
        getCommand("systeminfo").setExecutor(new org.codelaad.mcSystemInfo.commands.SystemInfoCommand(this));
    }

    private void createConfig() {
        configFile = new File(getDataFolder(), "config.yml");

        // Ensure the plugin folder exists
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // If the config file does not exist, create it and write default values
        if (!configFile.exists()) {
            getLogger().info("Generating default config.yml...");
            saveResource("config.yml", false);
        }

        // Load the config file
        config = YamlConfiguration.loadConfiguration(configFile);

        // Load default values from internal resource
        InputStream defaultConfigStream = getResource("config.yml");
        if (defaultConfigStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultConfigStream));
            config.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getPluginConfig() {
        return config;
    }

    @Override
    public void onDisable() {
        getLogger().info("MCSysInfo Plugin has been disabled.");
    }
}
