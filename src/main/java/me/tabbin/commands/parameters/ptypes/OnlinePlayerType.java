package me.tabbin.commands.parameters.ptypes;

import me.tabbin.commands.HojaCommand;
import me.tabbin.commands.parameters.PTypeI;
import me.tabbin.config.configs.MessageConfig;
import me.tabbin.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OnlinePlayerType implements PTypeI<Player> {

    private static OnlinePlayerType i = new OnlinePlayerType();
    public static OnlinePlayerType get() { return i; }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    public Player getDefaultValue() {
        return null;
    }

    @Override
    public Player parse(HojaCommand command, String arg) {
        if(Bukkit.getPlayer(arg) == null || !Bukkit.getPlayer(arg).isOnline()){
            MessageUtil.msgConfig(command.getSender(), MessageConfig.get().InvalidTypeNotOnline, arg);
            return null;
        }
        return Bukkit.getPlayer(arg);
    }

    @Override
    public List<String> getTabList() {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
