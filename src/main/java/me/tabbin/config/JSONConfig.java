package me.tabbin.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import me.tabbin.HojaPlugin;
import me.tabbin.entity.Entity;
import me.tabbin.entity.adapters.EntityAdapter;
import me.tabbin.entity.adapters.LocationAdapter;
import me.tabbin.entity.adapters.RuntimeTypeAdapterFactory;
import org.bukkit.Location;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/*
Mainly used for entity storage in json format
 */
public class JSONConfig {

    @Getter
    private File file;
    @Getter
    private Gson gson;
    private HojaPlugin plugin;

    public JSONConfig(HojaPlugin plugin, Class type, String directory) {
        this.plugin = plugin;

        /*
        This part is essentially for polymorphism: entities were getting instantiated as their parent class,
        so now a 'type' string is stored with all entities and is used at serialization and deserialization time
        to instantiate the correct type of object
         */
        RuntimeTypeAdapterFactory<Entity> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Entity.class, "type");
        for (Class<Entity> entityClass : plugin.getEntityStorageManager().getEntityTypesRegister()) {
            runtimeTypeAdapterFactory = runtimeTypeAdapterFactory.registerSubtype(entityClass, entityClass.getSimpleName());
        }

        gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Location.class, new LocationAdapter())
               //
                //.registerTypeAdapter(type, new EntityAdapter(plugin, type))
                //.registerTypeAdapter(HGUIDesign.class, new HGUIDesignAdapter())
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory)
                .create();

        HojaPlugin.getHojaPlugin().log("Loading Config (" + type.getSimpleName() + ".json)");

        createDirectory(type.getSimpleName(), directory);
    }

    private void createDirectory(String name, String directory) {
        File directoryFile = new File(plugin.getDataFolder().getPath() + "/" + directory);
        if (!directoryFile.exists()) directoryFile.mkdir();
        //Creates the file
        file = new File(directoryFile.getPath() + "/" + name + ".json");
        file.getParentFile().mkdirs();

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

    public <T> T read(Type type) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            JsonReader jsonReader = new JsonReader(bufferedReader);
            return gson.fromJson(jsonReader, type);
        } catch (IOException e) {
            return null;
        }
    }
}
