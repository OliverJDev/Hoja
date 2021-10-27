package me.tabbin.config.configs;

import me.tabbin.HojaPlugin;
import me.tabbin.config.JSONConfig;

public class TestJson extends JSONConfig {

    public TestJson() {
        super(HojaPlugin.getHojaPlugin(), "test", "data");
    }
}
