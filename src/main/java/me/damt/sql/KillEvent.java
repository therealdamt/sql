package me.damt.sql;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class KillEvent implements Listener {

    private Main main;
    public KillEvent(Main main) {
        this.main = main;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        main.data.createPlayer(e.getPlayer());
    }


    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Player player = (Player) e.getEntity().getKiller();
        if (e.getEntity().getKiller() instanceof Player) {
        main.data.addPoints(player.getUniqueId(), 1);
            player.sendMessage(Util.chat("&7[&6&lPoints&7] &aPoint added!"));

        }
    }
}
