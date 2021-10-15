package me.tabbin.commands.parameters.ptypes;

import me.tabbin.commands.parameters.PTypeI;
import me.tabbin.config.configs.MessageConfig;
import me.tabbin.util.Utility;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerType implements PTypeI<Integer> {

    @Override
    public String getName() {
        return "Number";
    }

    @Override
    public Integer getDefaultValue() {
        return 0;
    }

    @Override
    public Integer parse(CommandSender sender, String arg) {
        if(!isInteger(arg)){
           // sender.sendMessage(Utility.addColor(MessageConfig.get().IntegerType0Invalid));
        }
        return null;
    }

    @Override
    public List<String> getTabList() {
        return new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    }

    private boolean isInteger(String args){
        try {
            Integer.parseInt(args);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
