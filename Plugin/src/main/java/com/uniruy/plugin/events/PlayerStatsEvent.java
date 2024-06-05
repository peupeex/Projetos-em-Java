package com.uniruy.plugin.events;

import com.uniruy.plugin.PluginApplication;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerStatsEvent implements Listener {

    private final PluginApplication plugin;

    public PlayerStatsEvent(PluginApplication plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        String playerUUID = p.getUniqueId().toString();
        String breakKey = playerUUID + ".totalBrokenBlocks";

        FileConfiguration config = plugin.getConfig();
        int brokenBlocks = config.getInt(breakKey, 0);

        brokenBlocks += 1;
        config.set(breakKey, brokenBlocks);
        plugin.saveConfig();
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        String playerUUID = p.getUniqueId().toString();
        String placeKey = playerUUID + ".totalPlacedBlocks";

        FileConfiguration config = plugin.getConfig();
        int placedBlocks = config.getInt(placeKey, 0);

        placedBlocks += 1;
        config.set(placeKey, placedBlocks);
        plugin.saveConfig();
    }

    @EventHandler
    public void onKillMobs(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            Player p = e.getEntity().getKiller();
            Entity mobs = e.getEntity();

            String playerUUID = p.getUniqueId().toString();
            String killKey = playerUUID + ".totalMobKills";

            FileConfiguration config = plugin.getConfig();
            int killedMobs = config.getInt(killKey, 0);


            killedMobs += 1;
            config.set(killKey, killedMobs);
            plugin.saveConfig();
        }


    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        String playerUUID = p.getUniqueId().toString();
        String deathKey = playerUUID + ".totalPlayerDeaths";

        FileConfiguration config = plugin.getConfig();
        int playerDeaths = config.getInt(deathKey, 0);

        playerDeaths += 1;
        config.set(deathKey, playerDeaths);
        plugin.saveConfig();
    }
}