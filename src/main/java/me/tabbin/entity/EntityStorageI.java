package me.tabbin.entity;

import java.util.Map;

public interface EntityStorageI {

    void addEntity(Entity entity, String id);
    Map<String, Entity> getAll();
    Entity get(String id);
}
