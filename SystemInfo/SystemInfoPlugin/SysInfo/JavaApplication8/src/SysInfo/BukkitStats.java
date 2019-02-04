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
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

public class BukkitStats {
    public BukkitStats() {
    }

    public static String getBVersion() {
        return Bukkit.getServer().getVersion();
    }

    public static String checkViaVersion() {
        return Bukkit.getPluginManager().getPlugin("ViaVersion") != null ? ChatColor.GREEN + "True" : ChatColor.RED + "False";
    }
}
