package io.slimemc.slimecore.compatibility;

import org.bukkit.Bukkit;

public enum ServerVersion {

    UNKNOWN, V1_8, V1_9, V1_10, V1_11, V1_12, V1_13, V1_14, V1_15, V1_16, V1_17, V1_18;

    private final static String serverPackageVersion = Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
    private final static ServerVersion serverVersion = getVersion();

    private static ServerVersion getVersion() {
        for (ServerVersion serverVersion : values()) {
            if (serverPackageVersion.toUpperCase().startsWith(serverVersion.name())) {
                return serverVersion;
            }
        }
        return UNKNOWN;
    }

    public static ServerVersion getServerVersion() {
        return serverVersion;
    }

    public static String getServerVersionString() {
        return serverPackageVersion;
    }

    public static boolean equals(ServerVersion version) {
        return serverVersion == version;
    }

    public static boolean isAbove(ServerVersion version) {
        return serverVersion.ordinal() > version.ordinal();
    }

    public static boolean isAtLeast(ServerVersion version) {
        return serverVersion.ordinal() >= version.ordinal();
    }

    public static boolean isBelow(ServerVersion version) {
        return serverVersion.ordinal() < version.ordinal();
    }

    public static boolean isAtOrBelow(ServerVersion version) {
        return serverVersion.ordinal() <= version.ordinal();
    }

}

