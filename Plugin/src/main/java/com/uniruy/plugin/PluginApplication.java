package com.uniruy.plugin;

import com.uniruy.plugin.commands.Comandos;
import com.uniruy.plugin.events.BiomeEvent;
import com.uniruy.plugin.events.PlayerStatsEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.uniruy.plugin.events.BlockBreakChainEvent;

import java.io.File;

public final class PluginApplication extends JavaPlugin {

    public void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new BiomeEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakChainEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerStatsEvent(this), this);

    }

    public void registerCommands(){
        this.getCommand("stats").setExecutor(new Comandos(this));
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("Plugin Ligado");
        registerEvents();
        registerCommands();
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Plugin Desligado");
    }
}

