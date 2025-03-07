# MCSysInfo - Task Manager and Resource Monitor Plugin

**MCSystemInfo** aka: SysInfo is a Spigot Plugin for Minecraft ~~1.14.4~~ 1.20.1 (v2) designed to provide live system performance metrics directly within Minecraft. It offers detailed insights into server CPU usage, RAM allocation, disk space, and TPS (ticks per second), helping server administrators monitor performance efficiently. Future updates will include, a limited task manager to carry out tasks and the ability to access Linux 'screen' tasks. 

## Changelog:
v1: Minecraft 1.8 (Launch)
v1.3: Minecraft 1.13.2 (added support for CentOS 6
v1.3.1: Minecraft 1.14 (changed GNULinux support to use Debian over CentOS)
v1.4: (increased support list: CentOS (5, 6 & 7), Debian (7 & 8), Ubuntu (12.04, 14.04 & 16.04), FreeBSD (12 & 13)
v1.4.1 (final build, adding support for **Minecraft 1.14.4**)
~~project was depreciated on May 14th 2019~~

SystemInfo 2.0
v2.0a: (Updated support to Minecraft 1.20.1) // resolved autofill commands 
v2.1a: // TBD: Reimplementing Task Manager support

## Features
- **Real-Time CPU Usage Monitoring**
- **Memory Usage Statistics (RAM, Garbage Collection, Heap Usage)**
- **TPS (Ticks Per Second) Analysis for Performance Monitoring**
- **Hard Drive Storage Details**
- **Plugin Integration with Bukkit/Spigot API**
- **Lightweight and Optimized for Performance**

- **Language:** Java
- **Platform:** PaperSpigot (formally: Spigot)
- **Build Tool:** Maven / Ant

## Prerequisites
- **Minecraft Server:** Spigot or PaperMC (1.20.1) 
- **Java Development Kit (JDK 16 or later)**
- **Maven or Ant for building the plugin**

## Setup & Installation
1. **Clone the Repository:**
   ```sh
   git clone https://github.com/MichaelSoftware/SysInfo.git
   cd SysInfo
   ```
2. **Build the Plugin:**
   - If using **Maven**:
     ```sh
     mvn clean package
     ```
   - If using **Ant**:
     ```sh
     ant build
     ```
3. **Retrieve the Compiled JAR:**
   - The compiled plugin JAR file will be located in `target/` (Maven) or `dist/` (Ant).
4. **Install the Plugin:**
   - Copy the JAR file into your Minecraft server's `plugins` directory.
5. **Start or Restart the Server:**
   - Load the plugin by restarting or reloading your server.

## Commands & Permissions (SysInfo 1.0)
- **/sysinfo cpu** - Displays current CPU usage.
- **/sysinfo ram** - Shows RAM usage statistics.
- **/sysinfo tps** - Checks the server’s TPS (ticks per second).
- **/sysinfo storage** - Displays available hard drive storage.
- **Permissions:**
  - `sysinfo.view` - Grants access to view system statistics.
  - `sysinfo.admin` - Provides admin privileges for plugin settings.

## Contribution Guidelines
1. **Fork the repository.**
2. **Create a feature branch:** `git checkout -b feature-branch`
3. **Commit changes:** `git commit -m "Description of changes"`
4. **Push the branch:** `git push origin feature-branch`
5. **Submit a Pull Request for review.**

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For inquiries or support, open an issue in the repository or contact the maintainer:
- **CodeLaad**: [GitHub Profile](https://github.com/MichaelSoftware)


