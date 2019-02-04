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
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import javax.management.MBeanServerConnection;

public class CPU {
    public CPU() {
    }

    public static String getCores() {
        Runtime r = Runtime.getRuntime();
        int cores = r.availableProcessors();
        return String.valueOf(cores);
    }

    public static String CPULoad() throws IOException {
        MBeanServerConnection conn = ManagementFactory.getPlatformMBeanServer();
        OperatingSystemMXBean os = (OperatingSystemMXBean)ManagementFactory.newPlatformMXBeanProxy(conn, "java.lang:type=OperatingSystem", OperatingSystemMXBean.class);
        return os.getSystemLoadAverage() + "%";
    }
}

