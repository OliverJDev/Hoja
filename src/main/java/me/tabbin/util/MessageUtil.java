package me.tabbin.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageUtil {


    public static String parseString(String config, Object... args){
        String newString = config;
        List<Object> arguments = new ArrayList<>(Arrays.asList(args));
        for(int i = 0; i < Utility.getWordCount(config, "%arg%"); i++){
            if(arguments.size() >0){
                newString = newString.replaceFirst("%arg%", arguments.get(i).toString());
            }else{
                break;
            }
        }
        return newString;
    }

    public static void msgAll(String config){
        msgAll(config, "");
    }
    public static void msgAll(String config, Object... args){
        Bukkit.getOnlinePlayers().forEach(p->msgConfig(p, config, args));
    }
    public static void msgConfig(Player player, String config){
        msgConfig(player, config ,"");
    }
    public static void msgConfig(Player player, String config, Object... args){
        if (config.equals("")) return;
        player.sendMessage(Utility.addColor(parseString(config, args)));
    }
    public static void msgConfig(CommandSender player, String config, Object... args){
        if (config.equals("")) return;
        player.sendMessage(Utility.addColor(parseString(config, args)));
    }

}
