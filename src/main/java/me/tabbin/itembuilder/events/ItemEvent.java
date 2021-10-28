package me.tabbin.itembuilder.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import me.tabbin.entity.Entity;
import org.bukkit.event.block.Action;


@Getter @Setter
public abstract class ItemEvent extends Entity<ItemEvent> implements ItemEventI{

    private String key = "reset-key";

    public ItemEvent(){

    }
    public ItemEvent(HojaPlugin plugin, String key){
        super(plugin, key);
        setKey(key);
    }

}
