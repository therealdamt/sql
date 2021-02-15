package me.damt.sql.events;

import me.damt.sql.SQL;
import me.damt.sql.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class KillEvent implements Listener {

    private final SQL sql;

    public KillEvent() {
        this.sql = SQL.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
       sql.getData().createPlayer(e.getPlayer());
    }


    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Player player = (Player) e.getEntity().getKiller();
        if (e.getEntity().getKiller() instanceof Player) {
            sql.getData().addPoints(player.getUniqueId(), 1);
            player.sendMessage(Util.chat("&7[&6&lPoints&7] &aPoint added!"));

        }
    }
}
