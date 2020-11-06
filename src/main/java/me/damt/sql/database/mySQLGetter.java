package me.damt.sql.database;


import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import me.damt.sql.Main;
import org.bukkit.entity.Player;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class mySQLGetter {
    private Main plugin;

    public mySQLGetter(Main plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS mobpoints " +
                    "(NAME VARCHAR(100),UUID VARCHAR(100),POINTS INT(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!exists(uuid)) {
                PreparedStatement ps1 = plugin.SQL.getConnection().prepareStatement("SELECT * FROM mobpoints WHERE UUID=?" );
                ps1.setString(1, player.getName());
                ps1.setString(2, uuid.toString());
                ps1.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean exists(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INFO mobpoints (NAME, UUID), VALUES (?,?)");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void addPoints(UUID uuid, int points) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE mobpoints SET POINTS=? WHERE UUID=?");
            ps.setInt(1, (getPoints(uuid)) + points);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getPoints(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT POINTS FROM mobpoints");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int points = 0;
            if (rs.next()) {
                points = rs.getInt("POINTS");
                return points;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
