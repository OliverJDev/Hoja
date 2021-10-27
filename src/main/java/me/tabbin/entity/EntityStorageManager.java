package me.tabbin.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import me.tabbin.HojaPluginI;
import me.tabbin.entity.test.TestEntity;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


public class EntityStorageManager implements EntityStorageManagerI{

    @Getter
    private LinkedList<Class<Entity>> entityTypesRegister = new LinkedList<>();
    @Getter
    private LinkedHashMap<Class, EntityStorage> entityStorages = new LinkedHashMap<>();

    private HojaPlugin instance;

    public EntityStorageManager(HojaPlugin plugin){
        instance = plugin;
    }

    public void addEntity(Entity entity){

        if(entityStorages.containsKey(entity.getClass())){
            if(entity.getId().equalsIgnoreCase("")){
                entityStorages.get(entity.getClass()).addEntity(entity, entity.getName());
            }else{
                entityStorages.get(entity.getClass()).addEntity(entity, entity.getId());
            }
        }else{
            entityStorages.put(entity.getClass(), new EntityStorage(instance, entity.getClass().getSuperclass()));
            addEntity(entity);
        }

    }

    @Override
    public void sync() {
        for (Map.Entry<Class, EntityStorage> classEntityStorageEntry : getEntityStorages().entrySet()) {
            EntityStorage storage = classEntityStorageEntry.getValue();
            storage.getConfig().write(storage.getAll());
        }
    }

    @Override
    public void addEntityType(Class type) {

        getEntityTypesRegister().add(type);
    }
}
