package me.tabbin.itembuilder.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.block.Action;

@Getter @Setter
public abstract class ItemClickEvent extends ItemEvent{
    Action clickType;

    public ItemClickEvent(String key, Action action) {
        super(key);
        setClickType(action);
    }
}
