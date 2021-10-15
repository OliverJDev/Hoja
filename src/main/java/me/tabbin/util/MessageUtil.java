package me.tabbin.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageUtil {

    public static void msgConfig(Player player, String config, Object... args){
        String newString = config;
        List<Object> arguments = new ArrayList<>(Arrays.asList(args));
        for(int i = 0; i > Utility.getWordCount(config, "%arg%"); i++){
            if(arguments.size() >0){
                newString = config.replaceFirst("%arg%", arguments.get(0).toString());
            }else{
                break;
            }
        }
        player.sendMessage(Utility.addColor(newString));
    }
    public static void msgConfig(CommandSender player, String config, Object... args){
        String newString = config;
        List<Object> arguments = new ArrayList<>(Arrays.asList(args));
        for(int i = 0; i < Utility.getWordCount(config, "%arg%"); i++){
            if(arguments.size() >0){
                newString = config.replaceFirst("%arg%", arguments.get(0).toString());
            }else{
                break;
            }
        }
        player.sendMessage(Utility.addColor(newString));
    }

}
