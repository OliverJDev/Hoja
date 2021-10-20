package me.tabbin.commands;

import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class HojaCommandBukkit extends Command implements PluginIdentifiableCommand {

    @Override
    public Plugin getPlugin() {
        return HojaPlugin.getHojaPlugin();
    }

    private HojaCommand hojaCommand;

    protected HojaCommandBukkit(HojaCommand hojaCommand, String command) {
        super(command, "", "usageMessage", new ArrayList<>());
        setHojaCommand(hojaCommand);
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        getHojaCommand().setSender((Player) sender);
        getHojaCommand().execute();
        return false;
    }
}
