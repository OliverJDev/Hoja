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
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public abstract class HojaCommand implements PluginIdentifiableCommand, HojaCommandI {

    private CommandSender sender;
    private Player player;
    // The commands the plugin will react to ie /help, /helps, /helper etc..
    private List<String> commands = new ArrayList<>();

    private List<Parameter<?>> parameters = new ArrayList<>();

    private List<String> arguments;

    public HojaCommand(String command) {
        HojaCommandRegister.getAllCommands().add(this);
        getCommands().add(command.toLowerCase());
        setSender(getSender());
        if(getSender() instanceof Player){
            setPlayer((Player) getSender());
        }
    }

    public HojaCommand(String... commands) {
        HojaCommandRegister.getAllCommands().add(this);
        if (commands != null) setCommands(Arrays.asList(commands));
        setSender(getSender());
        if(getSender() instanceof Player){
            setPlayer((Player) getSender());
        }
    }
    public HojaCommand(List<String> commands) {
        HojaCommandRegister.getAllCommands().add(this);
        if (commands != null) setCommands(commands);
        setSender(getSender());
        if(getSender() instanceof Player){
            setPlayer((Player) getSender());
        }
    }

    public <T> void addParameter(T defaultType, PTypeI<T> type, String name){
        getParameters().add(new Parameter<>(defaultType, type, name));
    }

    public <T> void addParameter(T defaultType, PTypeI<T> type, String name, boolean concatenateFromHere){
        Parameter<?> param = new Parameter<>(defaultType, type, name);
        param.setConcat(true);
        getParameters().add(param);
    }
    public <T> T readArg(int pos){
        Parameter<T> parameter = (Parameter<T>) parameters.get(pos);
        String arg = arguments.get(pos);
        if(parameter.isConcat()){
            StringBuilder concatted = new StringBuilder();
            for (int j = pos; j < arguments.size(); j++) {
                if(pos != j){
                    concatted.append(" ");
                }
                concatted.append(arguments.get(j));
            }
            arg = concatted.toString();
        }

        return parameter.getType().parse(this, arg);
    }

    @Override
    public Plugin getPlugin() {
        return HojaPlugin.getHojaPlugin();
    }


    public void msgSender(String config, Object... args){
        MessageUtil.msgConfig(sender, config, args);
    }

    public void msgSender(String message){
        MessageUtil.msg(sender, message);
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
