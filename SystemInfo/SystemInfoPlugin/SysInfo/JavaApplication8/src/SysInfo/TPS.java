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
import net.minecraft.server.v1_12_R1.MinecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TPS {
    public TPS() {
    }

    public static String getTPS(Player p) {
        StringBuilder sb = new StringBuilder();
        double[] var5;
        int var4 = (var5 = MinecraftServer.getServer().recentTps).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Double tps = var5[var3];
            sb.append(format(tps));
            sb.append(", ");
        }

        String msg = sb.substring(0, sb.length() - 2);
        return msg;
    }

    private static String format(double tps) {
        return (tps > 18.0D ? ChatColor.GREEN : (tps > 16.0D ? ChatColor.YELLOW : ChatColor.RED)).toString() + (tps > 20.0D ? "*" : "") + Math.min((double)Math.round(tps * 100.0D) / 100.0D, 20.0D);
    }
}

