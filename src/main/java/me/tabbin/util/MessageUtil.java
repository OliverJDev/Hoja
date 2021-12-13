package me.tabbin.util;

import me.tabbin.enums.DefaultFontInfo;
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
    private final static int CENTER_PX = 154;

    public static void sendCenteredMessage(Player player, String message, int add, int remove){
        if(message == null || message.equals("")) player.sendMessage("");
        message = Utility.addColor(message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int center = CENTER_PX;
        center+=add;
        center-=remove;
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = center - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    public static void msg(Player player, String message){
        player.sendMessage(Utility.addColor(message));
    }

    public static void msg(CommandSender player, String message){
        player.sendMessage(Utility.addColor(message));
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
