package dev.jamieisgeek.bangui;

import dev.jamieisgeek.bangui.commands.BanGUICommand;
import dev.jamieisgeek.bangui.events.BanGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BanGUI extends JavaPlugin {


    private static BanGUI plugin;
    Logger logger = Bukkit.getLogger();


    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger logger = Bukkit.getLogger();

        // Get Events Listener
        getServer().getPluginManager().registerEvents(new BanGUIListener(), this);

        // Commands
        getCommand("ban").setExecutor(new BanGUICommand());

        // Plugin Online
        logger.info("=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        logger.info("BanGUI is Enabled");
        logger.info("Creator: JamieIsGeek");
        logger.info("Version: 1.0");
        logger.info("=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        logger.info("BanGUI is Disabled");
        logger.info("Thanks for using!");
        logger.info("Version: 1.0");
        logger.info("=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }
    public static BanGUI getPlugin() { return plugin; }
}
