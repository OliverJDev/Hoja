package me.tabbin;

import me.tabbin.entity.EntityStorage;

public interface HojaPluginI {

    void onEnableHojaPlugin();
    void onDisableHojaPlugin();

    void onPostEnableHojaPlugin();
    void onPostDisableHojaPlugin();

    void log(String msg);
    void saveEntity();

    void addActiveEntityStorage(EntityStorage<?> entityStorage);
}
