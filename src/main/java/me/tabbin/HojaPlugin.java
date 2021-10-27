package me.tabbin;

import com.google.gson.Gson;
import lombok.Getter;
import me.tabbin.commands.HojaCommandRegister;
import me.tabbin.commands.commands.TestCommand;
import me.tabbin.config.configs.MessageConfig;
import me.tabbin.config.configs.TestJson;
import me.tabbin.entity.Entity;
import me.tabbin.entity.EntityStorage;
import me.tabbin.entity.EntityStorageManager;
import me.tabbin.entity.test.TestEntity;
import me.tabbin.util.Utility;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.LinkedList;
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
        TestJson json = new TestJson();


        new TestEntity("asdas");
        new TestEntity("dagfv");
        json.write(getEntityStorageManager().getEntityStorages().get(TestEntity.class).getAll());

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
