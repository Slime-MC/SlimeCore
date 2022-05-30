package com.yannick.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class sCommand {

    private String command;
    private boolean allowConsole;

    private List<String> subCommand = new ArrayList<>();

    protected sCommand(boolean mainCommand, boolean allowConsole, String... command){
        this.allowConsole = allowConsole;
        if (!mainCommand) {
            this.subCommand = Arrays.asList(command);
        } else {
            this.command = Arrays.asList(command).get(0);
        }
    }

    public String getCommand() {
        return command;
    }

    public List<String> getSubCommand() {
        return subCommand;
    }

    public void addSubCommand(String command) {
        subCommand.add(command);
    }

    public boolean allowConsole() {
        return allowConsole;
    }

    protected abstract void runCommand(CommandSender sender, String... args);

    public abstract String getPermissionNode();

    public abstract String getSyntax();

    public abstract String getDescription();


}
