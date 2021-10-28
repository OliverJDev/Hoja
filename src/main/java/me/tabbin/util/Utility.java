package me.tabbin.util;

import org.bukkit.ChatColor;

import java.util.*;

public class Utility {

    public static String addColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
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
