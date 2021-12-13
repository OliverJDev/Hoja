package me.tabbin.config.configs;

import me.tabbin.HojaPlugin;
import me.tabbin.config.HojaConfig;

public class MessageConfig extends HojaConfig {

    private static MessageConfig i;
    public static MessageConfig get() { return i; }
    private static void set(MessageConfig conf) { i = conf; }

    public String InvalidType = "&7Invalid %arg% Format '&c%arg%&7'!";
    public String InvalidTypeNotOnline = "&cThe player %arg% is currently not online!";
    public String MissingArgumentCommand= "&7Incorrect Usage, correct usage &c%arg%&7! (Missing %arg%)";
    public String InvalidArgumentCommand = "&7Incorrect Usage, correct usage &c%arg%&7! (%arg%)";

    public MessageConfig(HojaPlugin plugin) {
        super("messages", plugin);
        set(this);
    }
}
