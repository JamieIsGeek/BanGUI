package dev.jamieisgeek.bangui.commands;

import dev.jamieisgeek.bangui.utils.BanMenuUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class BanGUICommand implements CommandExecutor {

    Logger logger = Bukkit.getLogger();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            BanMenuUtils.openBanMenu(p);
        } else {
            logger.warning("You need to run this command in-game");
        }

        return true;
    }
}
