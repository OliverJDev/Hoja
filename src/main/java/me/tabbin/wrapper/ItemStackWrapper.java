package me.tabbin.wrapper;

import lombok.*;
import me.tabbin.gui.design.HGUIDesign;
import me.tabbin.itembuilder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SerializableAs("Item")
public class ItemStackWrapper implements ConfigurationSerializable {


    private String material;
    private short damage;
    private int amount;
    private String name;
    private Map<String, Integer> enchants;
    private Map<String, String> nbtMap;
    private Set<ItemFlag> itemFlags;
    private List<String> lore;
    private Boolean unbreakable;

    public ItemStack toItemStack() {
        if (material == null) {
            return null;
        }
        ItemBuilder item = new ItemBuilder(Material.valueOf(material));
        if (amount != 0) {
            item = new ItemBuilder(Material.valueOf(material), amount);
        }
        if (enchants != null) {
            HashMap<Enchantment, Integer> map = new HashMap();
            item.addEnchantments(map);
        }
        if (name != null) {
            item.setName(name);
        }
        if (lore != null) {
            item.setLore(lore);
        }

        return item.toItemStack();
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        for (Field field : getClass().getDeclaredFields()) {
            try {
                if (field.get(this) != null) {
                    if (field.get(this) instanceof Number) {
                        if (((Number) field.get(this)).intValue() == 0) {
                            continue;
                        }
                    }
                    result.put(field.getName(), field.get(this));

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ItemStackWrapper deserialize(Map<String, Object> args) {

        ItemStackWrapper wrapper = builder().build();
        for (Map.Entry<String, Object> stringObjectEntry : args.entrySet()) {
            if (stringObjectEntry.getValue() != null) {
                for (Field field : wrapper.getClass().getDeclaredFields()) {
                    if (field.getName().equalsIgnoreCase(stringObjectEntry.getKey())) {
                        try {
                            field.setAccessible(true);
                            field.set(wrapper, stringObjectEntry.getValue());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return wrapper;
    }
}
