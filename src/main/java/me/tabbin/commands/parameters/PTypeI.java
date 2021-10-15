package me.tabbin.commands.parameters;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface PTypeI<T> {

    String getName();
    T getDefaultValue();
    T parse(CommandSender sender, String arg);
    List<String> getTabList();
}
