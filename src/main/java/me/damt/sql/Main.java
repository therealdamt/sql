package me.damt.sql;

import me.damt.sql.database.mySQL;
import me.damt.sql.database.mySQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Main extends JavaPlugin {

    public mySQL SQL;
    public mySQLGetter data;
    public static Main instance;


    public void onEnable() {
        registerAll();
        this.SQL = new mySQL();
        instance = this;
        data = new mySQLGetter(this);

        try {
            SQL.connect();
        } catch (SQLException | ClassNotFoundException e) {
            getServer().getConsoleSender().sendMessage(Util.chat("&bSQL &cdatabase is not found!"));
            getServer().getConsoleSender().sendMessage(Util.chat("&7-----------------------------------"));

        }
        if (SQL.isConnected()) {
            getServer().getConsoleSender().sendMessage(Util.chat("&bSQL &adatabase is connected!"));
            getServer().getConsoleSender().sendMessage(Util.chat("&7-----------------------------------"));
            data.createTable();
        }
    }

    private void registerMessages() {
        getServer().getConsoleSender().sendMessage(Util.chat("&7-----------------------------------"));
        getServer().getConsoleSender().sendMessage(Util.chat("&6Test Plugin for &bSQL &6has loaded!"));
        getServer().getConsoleSender().sendMessage(Util.chat("&6Connecting to &bSQL&6....."));
        getServer().getConsoleSender().sendMessage(Util.chat("&7-----------------------------------"));

    }

    private void registerCommands() {
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new KillEvent(this), this);
    }

    private void registerAll() {
        registerCommands();
        registerListeners();
        registerMessages();
    }

    public static Main getInstance() {
        return instance;
    }
}
