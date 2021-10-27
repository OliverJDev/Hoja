package me.tabbin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.tabbin.HojaPlugin;
import me.tabbin.Identified;


@Getter @Setter
public class Entity<E> implements Identified{

    private String id = "";
    private String name = "";

    public Entity(String id, String name, HojaPlugin plugin){
        setId(id);
        setName(name);
        plugin.getEntityStorageManager().addEntity(this);
    }
    public Entity(HojaPlugin plugin, String id){
        setId(id);
        plugin.getEntityStorageManager().addEntity(this);
    }



}
