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
public class Ram {
    private static int mb = 1048576;

    public Ram() {

    }

    public static String getRamUsage() {
        Runtime runtime = Runtime.getRuntime();
        long freemem = runtime.freeMemory() / 1048576L;
        long maxmem = runtime.totalMemory() / 1048576L;
        long usedmem = Math.abs(maxmem - freemem);
        String ret = usedmem + "MB/" + maxmem + "MB";
        return ret;
    }

    public static String getTotalRam() {
        Runtime run = Runtime.getRuntime();
        long maxmem = run.maxMemory() / 1048576L;
        String totals = maxmem + "MB";
        return totals;
    }
}

