package me.tabbin.itembuilder.events;

import lombok.Getter;
import lombok.Setter;
import me.tabbin.HojaPlugin;
import org.bukkit.event.block.Action;

@Getter @Setter
public abstract class ItemClickEvent extends ItemEvent{
    Action clickType;

    public ItemClickEvent(){

    }
    public ItemClickEvent(String key, Action action, HojaPlugin plugin) {
        super(plugin, key);
        setClickType(action);
    }
}
