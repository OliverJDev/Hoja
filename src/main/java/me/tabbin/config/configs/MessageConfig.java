package me.tabbin.config.configs;

import me.tabbin.HojaPlugin;
import me.tabbin.config.HojaConfig;

public class MessageConfig extends HojaConfig {

    private static MessageConfig i;
    public static MessageConfig get() { return i; }
    private static void set(MessageConfig conf) { i = conf; }

    public String IntegerType0Invalid = "&7Invalid Number Format '&c%arg%&7'!";

    public MessageConfig(HojaPlugin plugin) {
        super("messages", plugin);
        set(this);
    }
}
