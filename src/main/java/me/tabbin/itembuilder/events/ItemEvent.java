package me.tabbin.itembuilder.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.block.Action;


@Getter @Setter
public abstract class ItemEvent implements ItemEventI{

    private String key = "reset-key";
    public ItemEvent(String key){
        setKey(key);
    }

}
