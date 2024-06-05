package com.uniruy.plugin.events;

import com.uniruy.plugin.PluginApplication;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class BiomeEvent implements Listener {
    private final PluginApplication plugin;

    public BiomeEvent(PluginApplication plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Biome biome = event.getTo().getBlock().getBiome();

        String playerUUID = player.getUniqueId().toString();
        String achievementKey = playerUUID + ".biomes";

        FileConfiguration config = plugin.getConfig();
        List<String> visitedBiomes = config.getStringList(achievementKey);

        if (!visitedBiomes.contains(biome.toString())) {
            visitedBiomes.add(biome.toString());
            config.set(achievementKey, visitedBiomes);
            plugin.saveConfig();

            player.sendMessage("Você visitou o bioma: " + biome.toString()+ " pela primeira vez!");
        }
    }
}

