package me.tabbin.commands;

import lombok.Getter;
import me.tabbin.HojaPlugin;
import me.tabbin.util.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.*;

public class HojaCommandRegister extends BukkitRunnable {

    @Getter
    private static final transient Set<HojaCommand> allCommands = new HashSet<>();

    public HojaCommandRegister(){
        this.runTaskTimer(HojaPlugin.getHojaPlugin(), 0, 20);
    }

    @Override
    public void run() {
        Server server = Bukkit.getServer();
        SimpleCommandMap field = ReflectionUtil.getField(ReflectionUtil.getField(Bukkit.getServer().getClass(), "commandMap"), server);
        SimpleCommandMap simpleCommandMap = field;
        Map<String, Command> currentBukkitCommands = ReflectionUtil.getField(ReflectionUtil.getField(SimpleCommandMap.class, "knownCommands"), simpleCommandMap);
        Map<String, HojaCommand> newHojaCommands = new HashMap<>();

        for (HojaCommand allCommand : allCommands) {
            for (String command : allCommand.getCommands()) {
                newHojaCommands.put(command.trim().toLowerCase(), allCommand);
            }
        }

        for (Map.Entry<String, HojaCommand> stringHojaCommandEntry : newHojaCommands.entrySet()) {
            String commandString = stringHojaCommandEntry.getKey();
            HojaCommand commandObject = stringHojaCommandEntry.getValue();
            Command alreadyRegistered = currentBukkitCommands.get(commandString);

            if(!(alreadyRegistered instanceof HojaCommandBukkit) || ((HojaCommandBukkit) alreadyRegistered).getHojaCommand() != commandObject ){
                if(alreadyRegistered !=null){
                    currentBukkitCommands.remove(commandString);
                    alreadyRegistered.unregister(simpleCommandMap);
                }

            }else{
                continue;
            }

            HojaCommandBukkit hojaCommandBukkit = new HojaCommandBukkit(commandObject, commandString);

            String pluginName = (HojaPlugin.getHojaPlugin() != null ? HojaPlugin.getHojaPlugin().getName() : "MassiveCore");
            simpleCommandMap.register(pluginName, hojaCommandBukkit);
        }

        Iterator<Map.Entry<String, Command>> iter = currentBukkitCommands.entrySet().iterator();
        while (iter.hasNext())
        {
            Map.Entry<String, Command> entry = iter.next();
            String name = entry.getKey();
            Command command = entry.getValue();

            if(!(command instanceof HojaCommandBukkit))continue;
            if(newHojaCommands.containsKey(name))continue;

            command.unregister(simpleCommandMap);
            iter.remove();
        }
    }
}
