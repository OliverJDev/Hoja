package me.tabbin.entity;

import me.tabbin.HojaPlugin;

import java.util.HashMap;
import java.util.Map;

public class EntityStorage<E> implements EntityStorageI<E> {

    private Map<String, E> entityList = new HashMap<>();

    public EntityStorage(){
        //HojaPlugin.getHojaPlugin().addActiveEntityStorage(this);
    }

    @Override
    public Map<String, E> getAll() {
        return entityList;
    }

    @Override
    public E get(String id) {
        return entityList.get(id);
    }
}
