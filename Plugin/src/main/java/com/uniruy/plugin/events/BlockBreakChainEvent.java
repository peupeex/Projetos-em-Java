package com.uniruy.plugin.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakChainEvent implements Listener {

    public static List<Material> madeiras;
    public static List<Material> minerios;

    public BlockBreakChainEvent() {
        minerios = new ArrayList<>();
        minerios.add(Material.DIAMOND_ORE);
        minerios.add(Material.IRON_ORE);
        minerios.add(Material.GOLD_ORE);
        minerios.add(Material.LAPIS_ORE);
        minerios.add(Material.REDSTONE_ORE);
        minerios.add(Material.ANCIENT_DEBRIS);
        minerios.add(Material.NETHER_QUARTZ_ORE);
        minerios.add(Material.NETHER_GOLD_ORE);
        minerios.add(Material.COAL_ORE);

        madeiras = new ArrayList<>();
        madeiras.add(Material.ACACIA_LOG);
        madeiras.add(Material.BIRCH_LOG);
        madeiras.add(Material.DARK_OAK_LOG);
        madeiras.add(Material.JUNGLE_LOG);
        madeiras.add(Material.OAK_LOG);
        madeiras.add(Material.SPRUCE_LOG);
        madeiras.add(Material.ACACIA_LEAVES);
        madeiras.add(Material.BIRCH_LEAVES);
        madeiras.add(Material.DARK_OAK_LEAVES);
        madeiras.add(Material.OAK_LEAVES);
        madeiras.add(Material.JUNGLE_LEAVES);
        madeiras.add(Material.SPRUCE_LEAVES);
    }

    public static void breakWood(Block wood) {

        if (madeiras.contains((wood.getType()))) {
            wood.breakNaturally();
            BlockFace[] var1 = BlockFace.values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                BlockFace face = var1[var3];
                breakWood(wood.getRelative(face));
            }
        }
    }

    public static void breakOre(Block ore) {

        if (minerios.contains(ore.getType())) {
            ore.breakNaturally();
            BlockFace[] var1 = BlockFace.values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                BlockFace face = var1[var3];
                breakOre(ore.getRelative(face));
            }


        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        Block b = e.getBlock();
        if (p.isSneaking()) {
            if (madeiras.contains(b.getType())) {
                breakWood(b);
            }
            if (minerios.contains(b.getType())) {
                breakOre(b);
            }
        }
    }
}