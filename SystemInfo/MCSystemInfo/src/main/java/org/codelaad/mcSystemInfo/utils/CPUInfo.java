package org.codelaad.mcSystemInfo.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class CPUInfo {
    private static final SystemInfo systemInfo = new SystemInfo();
    private static final CentralProcessor processor = systemInfo.getHardware().getProcessor();
    private static long[][] prevTicks = processor.getProcessorCpuLoadTicks(); // Store per-core ticks

    public static double getCPULoad() {
        try {
            Thread.sleep(500); // Allow CPU usage to calculate
        } catch (InterruptedException ignored) {}

        double load = processor.getSystemCpuLoadBetweenTicks(prevTicks[0]) * 100; // Use first core ticks
        prevTicks = processor.getProcessorCpuLoadTicks(); // Update tick data
        return load;
    }

    public static int getPhysicalCoreCount() {
        return processor.getPhysicalProcessorCount();
    }

    public static int getLogicalCoreCount() {
        return processor.getLogicalProcessorCount();
    }

    public static String getCPUModel() {
        return processor.getProcessorIdentifier().getName();
    }

    public static double getBaseSpeedGHz() {
        return processor.getProcessorIdentifier().getVendorFreq() / 1_000_000_000.0; // Convert Hz to GHz
    }

    public static double[] getPerCoreLoad() {
        try {
            Thread.sleep(500); // Allow per-core CPU calculation
        } catch (InterruptedException ignored) {}

        double[] loads = processor.getProcessorCpuLoadBetweenTicks(prevTicks); // Fix API usage
        prevTicks = processor.getProcessorCpuLoadTicks(); // Update tick data
        return loads;
    }

    public static String getGraphicalBar(double value) {
        int filledBars = (int) (value / 10); // Convert 100% to 10 bars
        return " [" + "■".repeat(filledBars) + "-".repeat(10 - filledBars) + "] " + String.format("%.1f", value) + "%";
    }
}
