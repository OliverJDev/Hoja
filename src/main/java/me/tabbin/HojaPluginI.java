package me.tabbin;

import me.tabbin.entity.EntityStorage;
import org.bukkit.event.Listener;

public interface HojaPluginI {

    HojaPlugin getPlugin();
    void onEnableHojaPlugin();
    void onDisableHojaPlugin();

    void onPostEnableHojaPlugin();
    void onPostDisableHojaPlugin();

    void log(String msg);

    void addListener(Listener listener);

}
