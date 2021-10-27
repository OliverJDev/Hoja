package me.tabbin.entity;

import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import me.tabbin.HojaPlugin;
import me.tabbin.config.JSONConfig;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EntityStorage implements EntityStorageI {

    @Getter
    private Class<Entity> type;

    private Map<String, Entity> entityList;
    @Getter
    private JSONConfig config;
    public EntityStorage(HojaPlugin instance, Class type){
        this.type = type;
        instance.getEntityStorageManager().getEntityStorages().put(type, this);
        config = new JSONConfig(instance, type.getSimpleName(), "data");

        Type listType = new TypeToken<Map<String, Entity>>(){}.getType();

        entityList = config.read(listType);
        if(entityList == null){entityList = new HashMap<>();}
    }

    @Override
    public void addEntity(Entity entity, String id) {
        entityList.put(id, entity);
    }

    @Override
    public Map<String, Entity> getAll() {
        return entityList;
    }

    @Override
    public Entity get(String id) {
        return entityList.get(id);
    }
}
