package io.slimemc.slimecore.command;

import io.slimemc.slimecore.utils.TextUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private JavaPlugin plugin;
    private sCommand mainCommand;
    private List<sCommand> commands = new ArrayList<>();
    private String incorrectMessage = "&8[&a"+plugin.getName()+"&8] &7The command you entered does not exist or is spelt incorrectly.";
    private String noPermissionMessage = "&8[&a"+plugin.getName()+"&8] &7You do not have permission to run this command.";

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
        

    }

    private void processRequirements(sCommand command, CommandSender sender, String[] strings) {
        if (!(sender instanceof Player) && command.allowConsole()) {
            sender.sendMessage("You must be a player to use this command.");
            return;
        }
        if (command.getPermissionNode() == null || sender.hasPermission(command.getPermissionNode())) {
            command.runCommand(plugin, sender, strings);
            return;
        }
        sender.sendMessage(TextUtils.formatText(noPermissionMessage));
    }

    public List<sCommand> getCommands() {
        return Collections.unmodifiableList(commands);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        for (io.slimemc.slimecore.command.sCommand sCommand : commands) {
            if (sCommand.getCommand() != null && sCommand.getCommand().equalsIgnoreCase(command.getName().toLowerCase())) {
                if (strings.length == 0) {
                    processRequirements(sCommand, sender, strings);
                    return true;
                }
            } else if (strings.length != 0 && getMainCommand() != null && getMainCommand().getCommand().equalsIgnoreCase(command.getName())) {
                String cmd = strings[0];
                String cmd2 = strings.length >= 2 ? String.join(" ", strings[0], strings[1]) : null;
                for (String cmds : sCommand.getSubCommand()) {
                    if (cmd.equalsIgnoreCase(cmds) || (cmd2 != null && cmd2.equalsIgnoreCase(cmds))) {
                        processRequirements(sCommand, sender, strings);
                        return true;
                    }
                }
            }
        }
        sender.sendMessage(TextUtils.formatText(incorrectMessage));
        return true;
    }

    public CommandManager setIncorrect(String message) {
        this.incorrectMessage = message;
        return this;
    }

    public CommandManager setNoPermission(String message) {
        this.noPermissionMessage = message;
        return this;
    }

    public CommandManager setMainCommand(sCommand command) {
        plugin.getCommand(command.getCommand()).setExecutor(this);
        this.mainCommand = command;
        return this;
    }

    public CommandManager addCommand(sCommand command) {
        commands.add(command);
        return this;
    }

    public sCommand getMainCommand() {
        return mainCommand;
    }
}
