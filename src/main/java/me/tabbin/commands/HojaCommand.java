package me.tabbin.commands;

import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import me.tabbin.util.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public abstract class HojaCommand implements PluginIdentifiableCommand, HojaCommandI {

    private CommandSender sender;
    // The commands the plugin will react to ie /help, /helps, /helper etc..
    private List<String> commands = new ArrayList<>();

    public HojaCommand(String command) {
        HojaCommandRegister.getAllCommands().add(this);
        getCommands().add(command);
        setSender(getSender());
    }

    public HojaCommand(List<String> commands) {
        HojaCommandRegister.getAllCommands().add(this);
        if (commands != null) setCommands(commands);
    }

    @Override
    public Plugin getPlugin() {
        return HojaPlugin.getHojaPlugin();
    }


    public void msgSender(String config, Object... args){
        MessageUtil.msgConfig(sender, config, args);
    }
}
