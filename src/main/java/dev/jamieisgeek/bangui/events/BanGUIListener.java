package dev.jamieisgeek.bangui.events;

import dev.jamieisgeek.bangui.utils.BanMenuUtils;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BanGUIListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Online Players")) {
            if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                Player whoToBan = p.getServer().getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
                BanMenuUtils.openPunishMenu(p, whoToBan);
                e.setCancelled(true);
            }
        } else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Pick a punishment")) {
            if(e.getCurrentItem().getType() == Material.BOOK) {
                e.setCancelled(true);
                String reason = e.getCurrentItem().getItemMeta().getDisplayName();

                String player = e.getClickedInventory().getItem(49).getItemMeta().getDisplayName();
                Player whoToBan = p.getServer().getPlayerExact(player);

                BanMenuUtils.openConfirmMenu(p, whoToBan, reason);
            }
        } else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Confirm Ban")) {
            if(e.getCurrentItem().getType() == Material.RED_WOOL) {
                BanMenuUtils.openBanMenu(p);
                e.setCancelled(true);
            } else if(e.getCurrentItem().getType() == Material.GREEN_WOOL) {
                String playerName = ChatColor.stripColor(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName());
                Player target = p.getServer().getPlayerExact(playerName);
                String mod = p.getDisplayName();
                String reason = e.getClickedInventory().getItem(4).getItemMeta().getLore().toString();
                String old1BanReason = ChatColor.stripColor(reason).replace("Ban Reason: ", "");
                String ban2Reason = old1BanReason.replace("[","");
                String banReason = ban2Reason.replace("]", "");

                BanMenuUtils.banPlayer(target, mod, banReason);
                e.setCancelled(true);
                p.closeInventory();
            } else {
                e.setCancelled(true);
            }
        }
    }
}
