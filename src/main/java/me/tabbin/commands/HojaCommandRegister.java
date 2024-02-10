package me.tabbin.commands;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.scheduler.BukkitRunnable;

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
