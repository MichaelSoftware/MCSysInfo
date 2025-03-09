package org.codelaad.mcSystemInfo.utils;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

public class RAMInfo {
    private static final SystemInfo systemInfo = new SystemInfo();
    private static final GlobalMemory memory = systemInfo.getHardware().getMemory();

    public static long getTotalSystemRAM() {
        return memory.getTotal() / 1_073_741_824; // Convert bytes to GB
    }

    public static long getUsedSystemRAM() {
        return (memory.getTotal() - memory.getAvailable()) / 1_073_741_824; // Used RAM in GB
    }

    public static long getTotalSwap() {
        return memory.getVirtualMemory().getSwapTotal() / 1_073_741_824; // Swap space in GB
    }

    public static long getUsedSwap() {
        return memory.getVirtualMemory().getSwapUsed() / 1_073_741_824; // Used swap in GB
    }

    public static long getAllocatedServerRAM() {
        return Runtime.getRuntime().totalMemory() / 1_073_741_824; // JVM allocated RAM (GB)
    }

    public static long getUsedServerRAM() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1_073_741_824; // JVM used RAM
    }

    public static double getSystemMemoryUsagePercentage() {
        return ((double) getUsedSystemRAM() / getTotalSystemRAM()) * 100;
    }

    public static double getServerMemoryUsagePercentage() {
        return ((double) getUsedServerRAM() / getAllocatedServerRAM()) * 100;
    }
}
