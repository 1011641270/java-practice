package com.config;

public class SysConfig extends AbsConfig {
    private static SysConfig config = new SysConfig("system.properties");

    private SysConfig(String fileName) {
        super(fileName, true);
    }

    public static SysConfig getInstance() {
        return config;
    }
}
