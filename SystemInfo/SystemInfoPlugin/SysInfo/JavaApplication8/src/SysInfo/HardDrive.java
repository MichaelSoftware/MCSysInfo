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
import java.io.File;
import java.text.DecimalFormat;
import org.apache.commons.io.FileSystemUtils;

public class HardDrive {
    public HardDrive() {
    }

    public static double getUsedSpace() {
        try {
            double freeds = (double)FileSystemUtils.freeSpaceKb((new File("/")).getAbsolutePath());
            double freedsgb = freeds / 1024.0D / 1024.0D;
            return Double.parseDouble((new DecimalFormat("##.#")).format(freedsgb));
        } catch (Exception var4) {
            return 0.0D;
        }
    }

    public static double getTotalSpace() {
        try {
            double total = (double)(new File("/")).getTotalSpace();
            double totalgb = total / 1024.0D / 1024.0D / 1024.0D;
            return Double.parseDouble((new DecimalFormat("##.#")).format(totalgb));
        } catch (Exception var4) {
            return 0.0D;
        }
    }

    public static double getLeftSpace() {
        double left = getTotalSpace() - getUsedSpace();
        return Double.parseDouble((new DecimalFormat("##.#")).format(left));
    }
}


    public static double getInfo

