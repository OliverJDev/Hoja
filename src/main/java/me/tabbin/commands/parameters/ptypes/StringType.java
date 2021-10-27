package me.tabbin.commands.parameters.ptypes;

import me.tabbin.commands.HojaCommand;
import me.tabbin.commands.parameters.PTypeI;
import me.tabbin.config.configs.MessageConfig;
import me.tabbin.util.MessageUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringType implements PTypeI<String> {


    private static StringType i = new StringType();
    public static StringType get() { return i; }

    @Override
    public String getName() {
        return "String";
    }

    @Override
    public String getDefaultValue() {
        return "";
    }

    @Override
    public String parse(HojaCommand command, String arg) {
        if (arg.equals("")) {
            MessageUtil.msgConfig(command.getSender(), MessageConfig.get().InvalidArgumentCommand, command.getCorrectUsage(), MessageUtil.parseString(MessageConfig.get().InvalidType, getName(), arg));
            return null;
        }
        return arg;

    }
    @Override
    public List<String> getTabList() {
        return new ArrayList<>(Arrays.asList());
    }

}
