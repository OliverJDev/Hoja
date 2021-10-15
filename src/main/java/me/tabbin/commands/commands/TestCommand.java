package me.tabbin.commands.commands;

import me.tabbin.commands.HojaCommand;
import me.tabbin.config.configs.MessageConfig;

public class TestCommand extends HojaCommand {

    public TestCommand(){
        super("Test");
    }

    @Override
    public void execute() {
        msgSender(MessageConfig.get().IntegerType0Invalid, "test");
    }
}
