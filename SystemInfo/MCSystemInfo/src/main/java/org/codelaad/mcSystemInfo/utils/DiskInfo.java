package org.codelaad.mcSystemInfo.utils;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

public class DiskInfo {
    private static final SystemInfo systemInfo = new SystemInfo();

    public static String getDiskDetails() {
        StringBuilder info = new StringBuilder();
        for (HWDiskStore disk : systemInfo.getHardware().getDiskStores()) {
            info.append("Drive: ").append(disk.getModel())
                    .append(" | Size: ").append(disk.getSize() / 1_073_741_824).append(" GB\\n");
        }
        return info.toString();
    }
}
