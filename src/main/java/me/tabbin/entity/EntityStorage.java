package me.tabbin.entity;

import me.tabbin.HojaPlugin;

import java.util.HashMap;
import java.util.Map;

public class EntityStorage implements EntityStorageI {

    private Class<Entity> type;

    private Map<String, Entity> entityList = new HashMap<>();

    public EntityStorage(HojaPlugin instance, Class type){
        this.type = type;
        instance.getEntityStorageManager().getEntityStorages().put(type, this);
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
