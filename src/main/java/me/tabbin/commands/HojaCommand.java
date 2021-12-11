package me.tabbin.commands;

import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import me.tabbin.commands.parameters.PTypeI;
import me.tabbin.commands.parameters.Parameter;
import me.tabbin.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public abstract class HojaCommand implements PluginIdentifiableCommand, HojaCommandI {

    private CommandSender sender;
    // The commands the plugin will react to ie /help, /helps, /helper etc..
    private List<String> commands = new ArrayList<>();

    private List<Parameter<?>> parameters = new ArrayList<>();

    private List<String> arguments;

    public HojaCommand(String command) {
        HojaCommandRegister.getAllCommands().add(this);
        getCommands().add(command.toLowerCase());
        setSender(getSender());
    }

    public HojaCommand(List<String> commands) {
        HojaCommandRegister.getAllCommands().add(this);
        if (commands != null) setCommands(commands);
    }

    public <T> void addParameter(T defaultType, PTypeI<T> type, String name){
        getParameters().add(new Parameter<>(defaultType, type, name));
    }

    public <T> T readArg(int pos){
        Parameter<T> parameter = (Parameter<T>) parameters.get(pos);
        return parameter.getType().parse(this, arguments.get(pos));
    }

    @Override
    public Plugin getPlugin() {
        return HojaPlugin.getHojaPlugin();
    }


    public void msgSender(String config, Object... args){
        MessageUtil.msgConfig(sender, config, args);
    }

    @Override
    public String getCorrectUsage() {
        StringBuilder parameters = new StringBuilder();
        for (Parameter<?> parameter : getParameters()) {
            parameters.append("<").append(parameter.getName().toLowerCase()).append("> ");
        }
        return "/" + commands.get(0) + " " + parameters.toString();
    }

}
