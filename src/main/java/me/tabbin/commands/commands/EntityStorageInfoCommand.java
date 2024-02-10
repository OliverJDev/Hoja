package me.tabbin.commands.commands;

import me.tabbin.HojaPlugin;
import me.tabbin.commands.HojaCommand;

public class EntityStorageInfoCommand extends HojaCommand {

    HojaPlugin plugin;
    public EntityStorageInfoCommand(String alias, HojaPlugin plugin) {
        super(alias);
        this.plugin = plugin;
    }

    @Override
    public void execute() {
        getPlayer().sendMessage("Active Storages - " + plugin.getEntityStorageManager().getEntityStorages().size() + "");
        plugin.getEntityStorageManager().getEntityStorages().forEach((k,v)->{
            getPlayer().sendMessage(k.getSimpleName() + " - (" + v.getAll().size() + ")");
        });

    }
}
