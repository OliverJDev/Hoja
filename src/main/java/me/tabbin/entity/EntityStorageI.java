package me.tabbin.entity;

import java.util.Map;

public interface EntityStorageI<T> {

    Map<String, T> getAll();
    T get(String id);
}
