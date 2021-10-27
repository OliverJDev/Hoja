package me.tabbin.itembuilder;


import lombok.Getter;
import lombok.experimental.UtilityClass;
import me.tabbin.itembuilder.events.ItemEvent;

import java.util.HashMap;

public class ItemEvents{

    @Getter
    private HashMap<String, ItemEvent> keys = new HashMap<>();

    public void addClick(String key, ItemEvent function) {
        keys.put(key,function);
    }

}
