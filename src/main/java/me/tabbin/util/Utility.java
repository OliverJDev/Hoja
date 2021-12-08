package me.tabbin.util;

import org.bukkit.ChatColor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static String addColor(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        message = translateHexColorCodes(message);
        return message;
    }
    //https://www.spigotmc.org/threads/hex-color-code-translate.449748/#post-3867804 from @Elementeral
    public static String translateHexColorCodes(String message)
    {
        final char COLOR_CHAR = ChatColor.COLOR_CHAR;
        final Pattern hexPattern = Pattern.compile("<#" + "([A-Fa-f0-9]{6})" + ">");
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    public static boolean getChance(int minimalChance) {
        Random random = new Random();
        return random.nextInt(99) + 1 <= minimalChance;
    }


    public static int getWordCount(String message, String word) {
        String[] characters = message.split("");
        int occurrences = 0;
        for (int i = 0; i < characters.length; i++) {
            if (word.split("")[0].equals(characters[i])) {
                for (int i1 = 0; i1 < word.split("").length; i1++) {
                    if (characters.length > i + i1 && word.split("").length > i1) {
                        if (characters[i + i1].equals(word.split("")[i1])) {
                            if (i1 == word.split("").length - 1) {
                                occurrences++;
                            }
                        } else {
                            break;
                        }
                    }

                }
            }
        }
        return occurrences;
    }

    public static <T> List<T> list(T... items) {
        List<T> ret = new ArrayList<>(items.length);
        Collections.addAll(ret, items);
        return ret;
    }

    public static <K, V> Map<K, V> map(K key1, V value1, Object... objects) {
        Map<K, V> ret = new HashMap<>();
        ret.put(key1, value1);
        Iterator iter = Arrays.asList(objects).iterator();

        while(iter.hasNext()) {
            K key = (K) iter.next();
            V value = (V) iter.next();
            ret.put(key, value);
        }

        return ret;
    }

}
