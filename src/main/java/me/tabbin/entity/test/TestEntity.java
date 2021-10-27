package me.tabbin.entity.test;

import me.tabbin.HojaPlugin;
import me.tabbin.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TestEntity extends Entity<TestEntity> {

    public int test = 10;
    public String yo = "hi";
   // public HLocation loaction = new HLocation(Bukkit.getWorld("world"),  200, 20, 20, 100, 20);
    public Location location = new Location(Bukkit.getWorld("world"),  200, 20, 20, 100, 20);
    public ItemStack item = new ItemStack(Material.BEDROCK);

    public TestEntity(String id){
        super(HojaPlugin.getHojaPlugin(), id);
    }


}
