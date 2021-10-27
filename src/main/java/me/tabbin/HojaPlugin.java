package me.tabbin;

import lombok.Getter;
import me.tabbin.commands.HojaCommandRegister;
import me.tabbin.commands.commands.TestCommand;
import me.tabbin.config.configs.MessageConfig;
import me.tabbin.entity.EntityStorageManager;
import me.tabbin.entity.test.TestEntity;
import me.tabbin.itembuilder.ItemBuilder;
import me.tabbin.itembuilder.ItemEvents;
import me.tabbin.itembuilder.ItemEventsListener;
import me.tabbin.itembuilder.events.ItemClickEvent;
import me.tabbin.util.Utility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class HojaPlugin extends JavaPlugin implements HojaPluginI {

    @Getter
    private static HojaPlugin hojaPlugin;

    @Override
    public HojaPlugin getPlugin() {
        return this;
    }

    @Getter
    private String pluginName;

    @Getter
    private EntityStorageManager entityStorageManager;
    @Getter
    private ItemEvents itemEvents;

    private List<Listener> listeners = new ArrayList<>();

    @Getter
    private MessageConfig messageConfig;

    @Override
    public void onEnable() {
        if (this.getName().equalsIgnoreCase("hoja")) hojaPlugin = this;

        this.pluginName = this.getName();
        long enableTime = System.currentTimeMillis();
        log("&7*- &9Plugin Loading &7*-");

        //ENTITY STORAGE MANAGER
        this.entityStorageManager = new EntityStorageManager(this);

        //Item Builder events
        itemEvents = new ItemEvents();
        addListener(new ItemEventsListener(this));

        onEnableHojaPlugin();

        //COMMAND REGISTER
        new HojaCommandRegister();


        //LISTENERS
        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }


        enableTime = (System.currentTimeMillis() - enableTime);

        onPostEnableHojaPlugin();
        log("&7*- &9Plugin Finished Loading &7(" + enableTime + "ms) *-");

    }

    @Override
    public void onDisable() {
        long disableTime = System.currentTimeMillis();
        log("&7*- &9Plugin Disabling &7*-");

        //SAVE DATA
        getEntityStorageManager().sync();
        onDisableHojaPlugin();
        disableTime = (System.currentTimeMillis() - disableTime);
        onPostDisableHojaPlugin();
        log("&7*- &9Plugin Finished Disabling &7(" + disableTime + "ms) *-");
    }


    @Override
    public void onEnableHojaPlugin() {
        /*
        Tests objects / classes
         */
        new TestCommand();
        this.messageConfig = new MessageConfig(this);


    }

    @Override
    public void onDisableHojaPlugin() {

    }

    @Override
    public void onPostEnableHojaPlugin() {

    }

    @Override
    public void onPostDisableHojaPlugin() {

    }

    @Override
    public void log(String msg) {
        getServer().getConsoleSender().sendMessage(Utility.addColor(ChatColor.BLUE + pluginName + ChatColor.GRAY + " - " + ChatColor.BLUE + msg));
    }


    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }


}
