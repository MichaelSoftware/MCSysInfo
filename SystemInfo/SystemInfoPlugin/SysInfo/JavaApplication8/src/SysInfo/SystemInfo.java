/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysInfo;

/**
 *
 * @author michael
 */
import java.io.IOException;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SystemInfo extends JavaPlugin {
    public SystemInfo() {
    }

    public void onEnable() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        } else if (cmd.getName().equalsIgnoreCase("systeminfo")) {
            Player p = (Player)sender;
            if (p.hasPermission("admin.sysinfo")) {
                p.sendMessage(ChatColor.DARK_GRAY + "===================" + ChatColor.GOLD + "SystemInfo" + ChatColor.DARK_GRAY + "===================");
                p.sendMessage(ChatColor.GRAY + "Operating System :" + ChatColor.AQUA + System.getProperty("os.name"));
                p.sendMessage(ChatColor.GRAY + "OS version :" + ChatColor.AQUA + System.getProperty("os.version"));
                p.sendMessage(ChatColor.GRAY + "Free Disk Space :" + ChatColor.AQUA + HardDrive.getLeftSpace() + "GB" + "/" + HardDrive.getTotalSpace() + "GB");
                p.sendMessage(ChatColor.GRAY + "Used Disk Space :" + ChatColor.AQUA + HardDrive.getUsedSpace() + "GB");
                p.sendMessage(ChatColor.GRAY + "Total Disk Space :" + ChatColor.AQUA + HardDrive.getTotalSpace() + "GB");
                p.sendMessage(ChatColor.GRAY + "Ram Used :" + ChatColor.AQUA + Ram.getRamUsage());
                p.sendMessage(ChatColor.GRAY + "Total Ram :" + ChatColor.AQUA + Ram.getTotalRam());
                p.sendMessage(ChatColor.GRAY + "CPU Cores :" + ChatColor.AQUA + CPU.getCores());
                p.sendMessage(ChatColor.GRAY + "TPS :" + ChatColor.AQUA + TPS.getTPS(p));
                p.sendMessage(ChatColor.GRAY + "Bukkit Version :" + ChatColor.AQUA + BukkitStats.getBVersion());
                p.sendMessage(ChatColor.GRAY + "ViaVersion Enabled? :" + BukkitStats.checkViaVersion());

                try {
                    p.sendMessage(ChatColor.GRAY + "CPU Load :" + ChatColor.AQUA + CPU.CPULoad());
                    return true;
                } catch (IOException var7) {
                    p.sendMessage(ChatColor.GRAY + "CPU Load:" + ChatColor.RED + "Error!");
                    return true;
                }
            } else {
                p.sendMessage(ChatColor.RED + "You dont have permission to perform this command");
                return true;
            }
        } else {
            return true;
        }
    }
}

