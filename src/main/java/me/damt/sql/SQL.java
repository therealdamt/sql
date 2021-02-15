package me.damt.sql;

import me.damt.sql.database.mySQL;
import me.damt.sql.database.mySQLGetter;
import me.damt.sql.events.KillEvent;
import me.damt.sql.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class SQL extends JavaPlugin {

    private mySQL sql;
    private mySQLGetter data;
    private static SQL instance;

    public void onEnable() {
        instance = this;
        this.sql = new mySQL();
        this.data = new mySQLGetter();

        getServer().getConsoleSender().sendMessage(Util.chat("&7-----------------------------------"));
        getServer().getConsoleSender().sendMessage(Util.chat("&6Test Plugin for &bSQL &6has loaded!"));
        getServer().getConsoleSender().sendMessage(Util.chat("&6Connecting to &bSQL&6....."));
        getServer().getConsoleSender().sendMessage(Util.chat("&7-----------------------------------"));

        try {
            this.sql.connect();
        } catch (SQLException e) {
            getServer().getConsoleSender().sendMessage(Util.chat("&bSQL &cdatabase is not found!"));
            getServer().getConsoleSender().sendMessage(Util.chat("&7-----------------------------------"));

        }
        if (this.sql.isConnected()) {
            getServer().getConsoleSender().sendMessage(Util.chat("&bSQL &adatabase is connected!"));
            getServer().getConsoleSender().sendMessage(Util.chat("&7-----------------------------------"));
            data.createTable();
        }
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new KillEvent(), this);
    }

    public static SQL getInstance() {
        return instance;
    }

    public mySQL getSQL() {
        return this.sql;
    }

    public mySQLGetter getData() {
        return this.data;
    }

}
