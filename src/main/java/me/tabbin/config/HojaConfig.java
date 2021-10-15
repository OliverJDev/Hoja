package me.tabbin.config;


import me.tabbin.HojaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class HojaConfig extends CustomConfig{

    public HojaConfig(String name) {
        setName(name);
        HojaPlugin.getHojaPlugin().log("Loading Config (" + name +  ".yml)");
        new BukkitRunnable(){
            @Override
            public void run(){
                loadOrCreate();
            }
        }.runTaskLater(HojaPlugin.getHojaPlugin(), 5);
    }
}
