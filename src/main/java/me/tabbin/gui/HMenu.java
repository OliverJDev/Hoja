package me.tabbin.gui;

import lombok.Getter;
import me.tabbin.gui.design.HGUIDesign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class HMenu implements HMenuI {

    @Getter
    Inventory inventory;
    public HMenu(HGUIDesign design){
        inventory = design.build();
    }
    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }
}
