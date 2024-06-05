package com.uniruy.plugin.commands;

import com.uniruy.plugin.PluginApplication;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class Comandos implements CommandExecutor {



    private final PluginApplication plugin;

    public Comandos(PluginApplication plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

        if(cmd.getName().equalsIgnoreCase("stats")){
            if (args.length == 0){
                if(sender instanceof Player){
                    Player p = (Player) sender;
                    sendPlayerStats(sender, p.getUniqueId().toString(), p.getName());
                }else {
                    sender.sendMessage("§cVocê deve especificar um jogador ao usar esse comando");
                }
            } else if (args.length == 1) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                String targetUUID = target.getUniqueId().toString();

                if (targetUUID != null){
                    sendPlayerStats(sender, targetUUID.toString(), target.getName());
                }else {
                    sender.sendMessage("O jogador " + args[0] + "nunca entrou no servidor");
                }
            }
        }
        return false;


    }
    private void sendPlayerStats(CommandSender sender, String playerUUID, String playerName){
        FileConfiguration config = plugin.getConfig();
        String breakKey = playerUUID + ".totalBrokenBlocks";
        String placeKey = playerUUID + ".totalPlacedBlocks";
        String killKey = playerUUID + ".totalMobKills";
        String deathKey = playerUUID + ".totalPlayerDeaths";
        int brokenBlocks = config.getInt(breakKey, 0);
        int placedBlocks = config.getInt(placeKey, 0);
        int killedMobs = config.getInt(killKey, 0);
        int playerDeaths = config.getInt(deathKey, 0);
        sender.sendMessage("§nEstatísticas§r de §a" + playerName + "§r§f:\n\ntotal de blocos quebrados: " +
                brokenBlocks + "\ntotal de blocos colocados: "
                + placedBlocks + "\ntotal de mobs mortos: " + killedMobs + "\ntotal de §4mortes §fdo jogador: "
                + playerDeaths);
    }
}
