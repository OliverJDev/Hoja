package me.tabbin.commands.parameters;

import me.tabbin.commands.HojaCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface PTypeI<T> {


    String getName();
    T getDefaultValue();
    T parse(HojaCommand command, String arg);
    List<String> getTabList();
}
