package org.codelaad.mcSystemInfo.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.codelaad.mcSystemInfo.mcSystemInfo;
import org.codelaad.mcSystemInfo.utils.CPUInfo;
import org.codelaad.mcSystemInfo.utils.DiskInfo;
import org.codelaad.mcSystemInfo.utils.RAMInfo;
import oshi.SystemInfo;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SystemInfoCommand implements CommandExecutor, TabCompleter {
    private final mcSystemInfo plugin;
    private final Logger logger;

    public SystemInfoCommand(mcSystemInfo plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger(); // Get plugin logger
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        FileConfiguration config = plugin.getPluginConfig(); // Load plugin config

        // Debug log to check if command runs
        sender.sendMessage("§c[MCSysInfo] Command received: " + label + " " + String.join(" ", args));
        logger.info("[MCSysInfo] Command executed: " + label + " " + String.join(" ", args));

        if (args.length == 0) {
            sendMessage(sender, "Usage: /systeminfo <cpu|ram|storage|uptime>", NamedTextColor.RED);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "cpu":
                if (config.getBoolean("enable_cpu_info")) {
                    sendMessage(sender, "Fetching CPU Info...", NamedTextColor.YELLOW);
                    logger.info("[MCSysInfo] Executing /systeminfo cpu");
                    displayCPUInfo(sender, config);
                } else {
                    sendMessage(sender, "CPU info is disabled in config.", NamedTextColor.RED);
                }
                break;
            case "ram":
                if (config.getBoolean("enable_ram_info")) {
                    sendMessage(sender, "Fetching RAM Info...", NamedTextColor.YELLOW);
                    logger.info("[MCSysInfo] Executing /systeminfo ram");
                    displayRAMInfo(sender, config);
                } else {
                    sendMessage(sender, "RAM info is disabled in config.", NamedTextColor.RED);
                }
                break;
            case "storage":
                if (config.getBoolean("enable_storage_info")) {
                    sendMessage(sender, "Fetching Storage Info...", NamedTextColor.YELLOW);
                    logger.info("[MCSysInfo] Executing /systeminfo storage");
                    displayDiskInfo(sender);
                } else {
                    sendMessage(sender, "Storage info is disabled in config.", NamedTextColor.RED);
                }
                break;
            case "uptime":
                if (config.getBoolean("enable_uptime_info")) {
                    sendMessage(sender, "Fetching System Uptime...", NamedTextColor.YELLOW);
                    logger.info("[MCSysInfo] Executing /systeminfo uptime");
                    sendMessage(sender, "System Uptime: " + getUptime(), NamedTextColor.GREEN);
                } else {
                    sendMessage(sender, "Uptime info is disabled in config.", NamedTextColor.RED);
                }
                break;
            default:
                sendMessage(sender, "Invalid option. Use: /systeminfo <cpu|ram|storage|uptime>", NamedTextColor.RED);
        }
        return true;
    }

    private void displayCPUInfo(@NotNull CommandSender sender, FileConfiguration config) {
        sendMessage(sender, "===== CPU Information =====", NamedTextColor.GOLD);
        sendMessage(sender, "Model: " + CPUInfo.getCPUModel(), NamedTextColor.GREEN);
        sendMessage(sender, "Cores: " + CPUInfo.getPhysicalCoreCount() + " (" + CPUInfo.getLogicalCoreCount() + " Threads)", NamedTextColor.GREEN);
        sendMessage(sender, "Base Speed: " + String.format("%.2f GHz", CPUInfo.getBaseSpeedGHz()), NamedTextColor.GREEN);

        if (config.getBoolean("show_graphical_bars")) {
            sendMessage(sender, "Usage: " + CPUInfo.getGraphicalBar(CPUInfo.getCPULoad()), NamedTextColor.GREEN);
        } else {
            sendMessage(sender, "Usage: " + String.format("%.2f%%", CPUInfo.getCPULoad()), NamedTextColor.GREEN);
        }
    }

    private void displayRAMInfo(@NotNull CommandSender sender, FileConfiguration config) {
        sendMessage(sender, "===== RAM Information =====", NamedTextColor.GOLD);
        sendMessage(sender, "System Memory: " + RAMInfo.getUsedSystemRAM() + "GB / " + RAMInfo.getTotalSystemRAM() + "GB (" + String.format("%.1f", RAMInfo.getSystemMemoryUsagePercentage()) + "%)", NamedTextColor.GREEN);
        sendMessage(sender, "Swap Memory: " + RAMInfo.getUsedSwap() + "GB / " + RAMInfo.getTotalSwap() + "GB", NamedTextColor.GOLD);
        sendMessage(sender, "Minecraft Server Memory: " + RAMInfo.getUsedServerRAM() + "GB / " + RAMInfo.getAllocatedServerRAM() + "GB (" + String.format("%.1f", RAMInfo.getServerMemoryUsagePercentage()) + "%)", NamedTextColor.GREEN);
    }

    private void displayDiskInfo(@NotNull CommandSender sender) {
        sendMessage(sender, "===== Disk Usage =====", NamedTextColor.GOLD);
        sendMessage(sender, DiskInfo.getDiskDetails(), NamedTextColor.GREEN);
    }

    private String getUptime() {
        long uptime = new SystemInfo().getOperatingSystem().getSystemUptime();
        long days = uptime / 86400;
        long hours = (uptime % 86400) / 3600;
        long minutes = (uptime % 3600) / 60;
        long seconds = uptime % 60;

        return days + "d " + hours + "h " + minutes + "m " + seconds + "s";
    }

    private void sendMessage(CommandSender sender, String message, NamedTextColor color) {
        if (sender instanceof Player) {
            sender.sendMessage(Component.text(message, color)); // Adventure API for players
        } else {
            Bukkit.getConsoleSender().sendMessage("[MCSysInfo] " + message); // Console output
            logger.info("[MCSysInfo] " + message); // Plugin log
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("cpu", "ram", "storage", "uptime");
        }
        return null;
    }
}
