package me.tabbin.util;

import org.bukkit.ChatColor;

public class Utility {

    public static String addColor(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static int getWordCount(String message, String word){
        String[] characters = message.split("");
        int occurrences = 0;
        for (int i = 0; i < characters.length; i++) {
            if(word.split("")[0].equals(characters[i])){
                for (int i1 = 0; i1 < word.split("").length; i1++) {
                    if(characters[i + i1].equals(word.split("")[i1])){
                        if (i1 == word.split("").length -1) {
                            occurrences++;
                        }
                    }else{
                        break;
                    }
                }
            }
        }
        return occurrences;
    }


}
