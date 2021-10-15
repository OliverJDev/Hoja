package me.tabbin;

import com.google.gson.Gson;
import lombok.Getter;
import me.tabbin.commands.HojaCommandRegister;
import me.tabbin.commands.commands.TestCommand;
import me.tabbin.config.configs.MessageConfig;
import me.tabbin.entity.EntityStorage;
import me.tabbin.util.Utility;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;

public class HojaPlugin extends JavaPlugin implements HojaPluginI {

    @Getter
    private static HojaPlugin hojaPlugin;

    @Override
    public HojaPlugin getPlugin() {
        return this;
    }

    @Getter
    private String pluginName;
    private LinkedList<EntityStorage<?>> activeEntityStorages;

    private HojaCommandRegister commandRegister;


    @Getter
    private MessageConfig messageConfig;

    @Override
    public void onEnable() {
        if (this.getName().equalsIgnoreCase("hoja")) hojaPlugin = this;

        this.pluginName = this.getName();
        long enableTime = System.currentTimeMillis();
        log("&7*- &9Plugin Loading &7*-");

        this.activeEntityStorages = new LinkedList<>();
        onEnableHojaPlugin();

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
        saveEntity();
        onPostDisableHojaPlugin();
        log("&7*- &9Plugin Finished Disabling &7(" + disableTime + "ms) *-");
    }


    @Override
    public void onEnableHojaPlugin() {
        this.commandRegister = new HojaCommandRegister();
        this.messageConfig = new MessageConfig(this);

        //TEST
        new TestCommand();
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
    public void saveEntity() {
        Gson gson = new Gson();

        for (EntityStorage<?> activeEntityStorage : activeEntityStorages) {
            activeEntityStorage.getAll().forEach((id, obj) -> {
                getConfig().set("test-json", gson.toJson(obj));
            });
        }

        saveConfig();
    }

    @Override
    public void addActiveEntityStorage(EntityStorage<?> entityStorage) {
        activeEntityStorages.add(entityStorage);
    }


}
