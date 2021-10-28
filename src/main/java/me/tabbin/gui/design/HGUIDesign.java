package me.tabbin.gui.design;

import lombok.Getter;
import lombok.Setter;
import me.tabbin.util.Utility;
import me.tabbin.wrapper.ItemStackWrapper;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.Inventory;

import java.util.*;

@Getter @Setter @SerializableAs("GUI Design")
public class HGUIDesign implements HGUIDesignBuildI, ConfigurationSerializable {

    private Map<Character, ItemStackWrapper> key;
    private List<String> map;
    private String title;

    public HGUIDesign(){

    }

    public HGUIDesign(Map<Character, ItemStackWrapper> items, List<String> formatting, String title){
        this.key = items;
        this.map = formatting;
        this.title = title;
    }

    @Override
    public Inventory build() {
        int size = map.size() * 9;
        List<Character> slots = new ArrayList<>();
        for (String s : map) {
            for (String s1 : s.split("")) {
                slots.add(s1.charAt(0));
            }
        }

        Inventory inventory = Bukkit.createInventory(null, size, Utility.addColor(title));
        for (int i = 0; i < size; i++) {
            int row = (int) Math.ceil(i / 9);
            inventory.setItem(i, key.get(slots.get(row)).toItemStack());
        }
        return inventory;
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("key", key);
        result.put("map", map);
        result.put("title", title);
        return result;
    }
    public static HGUIDesign deserialize(Map<String, Object> args) {
        Map<Character, ItemStackWrapper> items = new HashMap<>();
        List<String> formatting = new ArrayList<>();
        String title = "";
        if(args.containsKey("key")) {
            ((Map<String, ItemStackWrapper>)args.get("key")).forEach((k,v)->{
                items.put(k.charAt(0), v);
            });
        }

        if(args.containsKey("map")) {
            formatting = ((List<String>)args.get("map"));
        }

        if(args.containsKey("title")) {
            title = args.get("title").toString();
        }

        return new HGUIDesign(items, formatting, title);
    }
}
