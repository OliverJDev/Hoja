package me.tabbin.commands.commands;

import me.tabbin.commands.HojaCommand;
import me.tabbin.commands.parameters.ptypes.IntegerType;
import me.tabbin.commands.parameters.ptypes.StringType;
import me.tabbin.config.configs.MessageConfig;

public class TestCommand extends HojaCommand {

    public TestCommand(){
        super("test");
        addParameter(0, IntegerType.get(), "Players Amount");
        addParameter("", StringType.get(), "Name Of Player");
    }

    @Override
    public void execute() {
        msgSender("WOOOO");
    }
}
