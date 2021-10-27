package me.tabbin.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import me.tabbin.HojaPlugin;
import me.tabbin.entity.adapters.LocationAdapter;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class JSONConfig {

    @Getter
    private File file;
    @Getter
    private FileConfiguration fileConfiguration;
    @Getter
    private Gson gson;
    private HojaPlugin plugin;


    public JSONConfig(HojaPlugin plugin, String name, String directory){
        this.plugin = plugin;
        gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Location.class, new LocationAdapter()).create();

        createDirectory(name, directory);
    }

    private void createDirectory(String name, String directory){
        File directoryFile = new File(plugin.getDataFolder().getPath() +"/"+ directory);
        if(!directoryFile.exists()) directoryFile.mkdir();
        //Creates the file
        file = new File(directoryFile.getPath() + "/" + name + ".json");

        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void write(Object object) {

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            gson.toJson(object, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T read(String id, Type type) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            JsonReader jsonReader = new JsonReader(bufferedReader);
            return gson.fromJson(jsonReader, type);
        } catch (IOException e) {
            return null;
        }
    }
}
