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
    private String type = this.getClass().getSimpleName();

    public Entity(){

    }
    public Entity(String id, String name, HojaPlugin plugin){
        setType(getClass().getSimpleName());
        setId(id);
        setName(name);
        plugin.getEntityStorageManager().addEntity(this);

    }

    public Entity(HojaPlugin plugin, String id){
        setType(getClass().getSimpleName());
        setId(id);
        plugin.getEntityStorageManager().addEntity(this);
    }



}
