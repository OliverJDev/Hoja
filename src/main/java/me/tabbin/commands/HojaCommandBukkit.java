package me.tabbin.commands;

import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import me.tabbin.commands.parameters.Parameter;
import me.tabbin.config.configs.MessageConfig;
import me.tabbin.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.command.defaults.PluginsCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter @Setter
public class HojaCommandBukkit extends BukkitCommand implements PluginIdentifiableCommand, TabCompleter {

    @Override
    public Plugin getPlugin() {
        return HojaPlugin.getHojaPlugin();
    }

    private HojaCommand hojaCommand;

    public HojaCommandBukkit(HojaCommand hojaCommand, String command) {
        super(command, "", "usageMessage", new ArrayList<>());
        setLabel(command);
        setUsage("/" + command);
        setHojaCommand(hojaCommand);

    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        getHojaCommand().setArguments(Arrays.asList(args));
        getHojaCommand().setSender((Player) sender);
        //check arguments
        for (int i = 0; i < getHojaCommand().getParameters().size(); i++) {
            if(i >= args.length){
                //missing argument
                MessageUtil.msgConfig(sender, MessageConfig.get().MissingArgumentCommand, hojaCommand.getCorrectUsage(), getHojaCommand().getParameters().get(i).getName());
                return true;
            }else{
                //check correct type
                if(getHojaCommand().getParameters().get(i).getType().parse(getHojaCommand(), args[i]) == null){
                    return true;
                }
            }
        }

        getHojaCommand().execute();
        return false;
    }


    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] rawArgs) throws IllegalArgumentException
    {
        // The JavaDocs for Command says these checks will be made.
        // So we should follow that contract.
        if (sender == null) throw new IllegalArgumentException("sender must not be null");
        if (rawArgs == null) throw new IllegalArgumentException("args must not be null");
        if (alias == null) throw new IllegalArgumentException("args must not be null");

        if(hojaCommand.getParameters().size() > rawArgs.length -1 ){
            Parameter<?> parameter = hojaCommand.getParameters().get(rawArgs.length - 1);
            return parameter.getType().getTabList();
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }
}
