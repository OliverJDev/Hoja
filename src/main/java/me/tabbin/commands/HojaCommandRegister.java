package me.tabbin.commands;

import lombok.Getter;
import me.tabbin.HojaPlugin;
import me.tabbin.util.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.ReloadCommand;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.*;

public class HojaCommandRegister extends BukkitRunnable {

    @Getter
    private static final transient List<HojaCommand> allCommands = new ArrayList<>();

    public HojaCommandRegister() {
        this.run();
    }

    @Override
    public void run() {
        for (HojaCommand allCommand : allCommands) {
            for (String command : allCommand.getCommands()) {
                HojaCommandBukkit hojaCommandBukkit = new HojaCommandBukkit(allCommand, command.trim().toLowerCase());
                ((CraftServer) Bukkit.getServer()).getCommandMap().register(command.trim().toLowerCase(), hojaCommandBukkit);

            }
        }
    }
}
