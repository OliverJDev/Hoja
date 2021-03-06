package me.tabbin.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface EntityStorageManagerI {
    LinkedHashMap<Class, EntityStorage>  getEntityStorages();
    void syncAll();
    void loadAll(Class type);
    void addEntityType(Class<Entity> type);
}
