package dev.jamieisgeek.bangui.utils;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.Date;

public class BanMenuUtils {
    ItemStack banMalicious;
    ItemStack banNonMalicious;
    ItemStack discrimination;
    ItemStack perma;


    public static void openBanMenu(Player p) {

        ArrayList<Player> list = new ArrayList<>(p.getServer().getOnlinePlayers());

        Inventory bangui;
        bangui = Bukkit.createInventory(p, 54, ChatColor.BLUE + "Online Players");

        for (Player player : list) {
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = playerHead.getItemMeta();

            meta.setDisplayName(player.getDisplayName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "Player Health: " + ChatColor.RED + player.getHealth());
            lore.add(ChatColor.GOLD + "Player EXP: " + ChatColor.RED + player.getExp());
            meta.setLore(lore);
            playerHead.setItemMeta(meta);

            bangui.addItem(playerHead);
        }
        p.openInventory(bangui);
    }

    public static void openPunishMenu(Player p, Player whoToBan) {
        /*
        Create the inventory to display all available punishments
        */

        Inventory punishGUI;
        punishGUI = Bukkit.createInventory(p, 54, ChatColor.RED + "Pick a punishment");


        // Create the player head of the selected player!

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta playerHeadMeta = playerHead.getItemMeta();
        playerHeadMeta.setDisplayName(whoToBan.getDisplayName());
        playerHead.setItemMeta(playerHeadMeta);
        punishGUI.setItem(49, playerHead);


        // Create the option for Malicious Hacks

        ItemStack banMalicious = new ItemStack(Material.BOOK, 1);
        ItemMeta banMaliciousMeta = banMalicious.getItemMeta();
        banMaliciousMeta.setDisplayName(ChatColor.WHITE + "Malicious Hacks");
        ArrayList<String> banMaliciousLore = new ArrayList<>();
        banMaliciousLore.add(ChatColor.GREEN + "Ban Length: " + ChatColor.WHITE + "14 Days");
        banMaliciousLore.add("");
        banMaliciousLore.add(ChatColor.GOLD + "Click to Select");
        banMaliciousMeta.setLore(banMaliciousLore);
        banMalicious.setItemMeta(banMaliciousMeta);
        punishGUI.addItem(banMalicious);

        // Create the option for Non-Malicious Hacks
        ItemStack banNonMalicious = new ItemStack(Material.BOOK, 1);
        ItemMeta banNonMaliciousMeta = banNonMalicious.getItemMeta();
        banNonMaliciousMeta.setDisplayName(ChatColor.WHITE + "Non-Malicious Hacks");
        ArrayList<String> banNonMaliciousLore = new ArrayList<>();
        banNonMaliciousLore.add(ChatColor.GREEN + "Ban Length: " + ChatColor.WHITE + "7 Days");
        banNonMaliciousLore.add("");
        banNonMaliciousLore.add(ChatColor.GOLD + "Click to Select");
        banNonMaliciousMeta.setLore(banNonMaliciousLore);
        banNonMalicious.setItemMeta(banNonMaliciousMeta);
        punishGUI.addItem(banNonMalicious);

        // Create the option for Discrimination
        ItemStack discrimination = new ItemStack(Material.BOOK, 1);
        ItemMeta discriminationMeta = discrimination.getItemMeta();
        discriminationMeta.setDisplayName(ChatColor.WHITE + "Transphobia, Homophobia, Slurs");
        ArrayList<String> discriminationLore = new ArrayList<>();
        discriminationLore.add(ChatColor.GREEN + "Ban Length: " + ChatColor.WHITE + "Permanent");
        discriminationLore.add("");
        discriminationLore.add(ChatColor.GOLD + "Click to Select");
        discriminationMeta.setLore(discriminationLore);
        discrimination.setItemMeta(discriminationMeta);
        punishGUI.addItem(discrimination);

        // Create the option for general perma ban
        ItemStack perma = new ItemStack(Material.BOOK, 1);
        ItemMeta permaMeta = perma.getItemMeta();
        permaMeta.setDisplayName(ChatColor.WHITE + "Permanent Ban");
        ArrayList<String> permaLore = new ArrayList<>();
        permaLore.add(ChatColor.GREEN + "Ban Length: " + ChatColor.WHITE + "Permanent");
        permaLore.add("");
        permaLore.add(ChatColor.GOLD + "Click to select");
        permaMeta.setLore(permaLore);
        perma.setItemMeta(permaMeta);
        punishGUI.addItem(perma);

        p.openInventory(punishGUI);
    }

    public static void openConfirmMenu(Player p, Player whoToBan, String reason) {
        Inventory confirmBanMenu = Bukkit.createInventory(p, 9, ChatColor.DARK_RED + "Confirm Ban");

        // Confirm Ban Option
        ItemStack ban = new ItemStack(Material.GREEN_WOOL, 1);
        ItemMeta ban_meta = ban.getItemMeta();
        ban_meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Confirm");
        ban.setItemMeta(ban_meta);
        confirmBanMenu.setItem(0, ban);
        confirmBanMenu.setItem(1, ban);
        confirmBanMenu.setItem(2, ban);
        confirmBanMenu.setItem(3, ban);

        // Add punishment type

        ItemStack playerInfo = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta playerInfoMeta = playerInfo.getItemMeta();
        playerInfoMeta.setDisplayName(whoToBan.getDisplayName());
        ArrayList<String> infoLore = new ArrayList<>();
        infoLore.add(ChatColor.GREEN + "Ban Reason: " + ChatColor.WHITE + reason);
        playerInfoMeta.setLore(infoLore);
        playerInfo.setItemMeta(playerInfoMeta);

        confirmBanMenu.setItem(4, playerInfo);

        // Cancel Ban Option
        ItemStack cancel = new ItemStack(Material.RED_WOOL, 1);
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Go Back!");
        cancel.setItemMeta(cancel_meta);
        confirmBanMenu.setItem(5, cancel);
        confirmBanMenu.setItem(6, cancel);
        confirmBanMenu.setItem(7, cancel);
        confirmBanMenu.setItem(8, cancel);

        p.openInventory(confirmBanMenu);
    }

    public static void banPlayer(Player target, String mod, String banReason) {
        /*
        This is where the player will be banned from!
        */

        Player p = target.getServer().getPlayerExact(mod);

        if(banReason.equalsIgnoreCase("Malicious Hacks")) {

            final long add = (long) (System.currentTimeMillis() + 1.21E+9);
            final Date expire = new Date(add);

            String banFormat = ChatColor.GREEN + "You have been banned from this server!" + "\nReason: " + ChatColor.WHITE + banReason + ChatColor.GREEN + "\nPunisher: " + ChatColor.WHITE + mod;

            target.getServer().getBanList(BanList.Type.NAME).addBan(target.getDisplayName(), banFormat, expire, mod);
            target.kickPlayer(banFormat);

            p.sendMessage(ChatColor.GREEN + "Banned Player: " + ChatColor.WHITE + target.getDisplayName() + ChatColor.GREEN + " For " + ChatColor.WHITE + banReason);
        } else if(banReason.equalsIgnoreCase("Non-Malicious Hacks")) {

            final long add = (long) (System.currentTimeMillis() + 6.048e+8);
            final Date expire = new Date(add);

            String banFormat = ChatColor.GREEN + "You have been banned from this server!" + "\nReason: " + ChatColor.WHITE + banReason + ChatColor.GREEN + "\nPunisher: " + ChatColor.WHITE + mod;

            target.getServer().getBanList(BanList.Type.NAME).addBan(target.getDisplayName(), banFormat, expire, mod);
            target.kickPlayer(banFormat);

            p.sendMessage(ChatColor.GREEN + "Banned Player: " + ChatColor.WHITE + target.getDisplayName() + ChatColor.GREEN + " For " + ChatColor.WHITE + banReason);
        } else if(banReason.equalsIgnoreCase("Transphobia, Homophobia, Slurs")){

            String banFormat = ChatColor.GREEN + "You have been banned from this server!" + "\nReason: " + ChatColor.WHITE + banReason + ChatColor.GREEN + "\nPunisher: " + ChatColor.WHITE + mod;

            target.getServer().getBanList(BanList.Type.NAME).addBan(target.getDisplayName(), banFormat, null, mod);
            target.kickPlayer(banFormat);

            p.sendMessage(ChatColor.GREEN + "Banned Player: " + ChatColor.WHITE + target.getDisplayName() + ChatColor.GREEN + " For " + ChatColor.WHITE + banReason);
        } else if(banReason.equalsIgnoreCase("Permanent Ban")) {
            String banFormat = ChatColor.GREEN + "You have been banned from this server!" + "\nReason: " + ChatColor.WHITE + banReason + ChatColor.GREEN + "\nPunisher: " + ChatColor.WHITE + mod;

            target.getServer().getBanList(BanList.Type.NAME).addBan(target.getDisplayName(), banFormat, null, mod);
            target.kickPlayer(banFormat);

            p.sendMessage(ChatColor.GREEN + "Banned Player: " + ChatColor.WHITE + target.getDisplayName() + ChatColor.GREEN + " For " + ChatColor.WHITE + banReason);
        }
    }
}
