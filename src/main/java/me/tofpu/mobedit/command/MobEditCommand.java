package me.tofpu.mobedit.command;

import me.tofpu.mobedit.MobEdit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MobEditCommand implements CommandExecutor {
    private final MobEdit mobEdit;

    public MobEditCommand(MobEdit mobEdit) {
        this.mobEdit = mobEdit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        mobEdit.reload();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have successfully reloaded MobEdit!"));
        return false;
    }
}
