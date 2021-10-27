package me.tabbin.itembuilder;

import de.tr7zw.nbtapi.NBTItem;
import lombok.AllArgsConstructor;
import me.tabbin.HojaPlugin;
import me.tabbin.itembuilder.events.ItemClickEvent;
import me.tabbin.itembuilder.events.ItemEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

@AllArgsConstructor
public class ItemEventsListener implements Listener {

    HojaPlugin plugin;


    //LEFT, RIGHT, MIDDLE CLICK ITEM
    @EventHandler
    public void onClickItem(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        NBTItem nbtItem = new NBTItem(event.getItem());
        if (!nbtItem.hasNBTData()) return;
        for (Map.Entry<String, ItemEvent> stringItemEventClickEntry : plugin.getItemEvents().getKeys().entrySet()) {
            if (stringItemEventClickEntry.getValue() instanceof ItemClickEvent) {
                ItemClickEvent itemClickEvent = (ItemClickEvent) stringItemEventClickEntry.getValue();
                if (itemClickEvent.getClickType() == event.getAction()) {
                    if (nbtItem.hasKey(itemClickEvent.getKey())) {
                        itemClickEvent.proc(event.getPlayer(), event);
                        break;
                    }
                }
            }
        }
    }
}
