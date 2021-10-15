package me.tabbin.entity;

import me.tabbin.Identified;


public class Entity<E> implements Identified, EntityI {

    private transient String id;
    private transient String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
