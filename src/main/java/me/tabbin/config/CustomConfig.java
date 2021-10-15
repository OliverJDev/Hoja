package me.tabbin.config;


import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomConfig {

    private String name;
    private FileConfiguration config;
    private File file;

    public void loadOrCreate() {
        file = new File(HojaPlugin.getHojaPlugin().getDataFolder(), name + ".yml");
        if (!file.exists()) {
            HojaPlugin.getHojaPlugin().saveResource(name + ".yml", false);
            file.getParentFile().mkdirs();
        }
        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        //load variables
        Field[] fields = this.getClass().getDeclaredFields();
        //LOAD ALL CONFIG VALUES FROM CONFIG AND CREATE IF NOT EXIST
        for (Field field : fields) {
            String name = field.getName();
            if(name.equals("i")){
                continue;
            }
            name = name.replaceAll("0", ".");
            field.setAccessible(true);

            if(getConfig().contains(name)){
                try {
                    field.set(this, getConfig().get(name));
                } catch (IllegalAccessException e) {
                    continue;
                }
            }else{
                try {
                    getConfig().set(name, field.get(this));
                } catch (IllegalAccessException e) {
                    continue;
                }
            }
        }
        List<String> keysDelete = new ArrayList<>();
        //DELETE UNUSED
        for (String key : config.getConfigurationSection("").getKeys(true)) {
            if(getConfig().get(key) !=null && getConfig().getConfigurationSection(key) ==null) {
                key = key.replaceAll("\\.", "0");
                boolean delete = true;
                for (Field field : fields) {
                    if (field.getName().equals(key)) {
                        delete = false;
                    }
                }
                if (delete) {
                    keysDelete.add(key);
                }
            }
        }
        for (String s : keysDelete) {
            getConfig().set(s, null);
        }

        saveConfig();


    }
    public void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reloadConfig() {
        file = new File(HojaPlugin.getHojaPlugin().getDataFolder(), name + ".yml");
        if (!file.exists()) {
            HojaPlugin.getHojaPlugin().saveResource(name + ".yml", false);
            file.getParentFile().mkdirs();
        }
        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
