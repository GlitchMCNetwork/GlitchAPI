package dev.selena.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ItemUtils implements Listener {

    /***
     * Deletes the item in the players hand
     * @param player The player you want to delete the item from
     * @param amount The amount of items you want to delete
     */
    public static void deleteItemInHand(Player player, int amount) {
        ItemStack item = player.getInventory().getItemInHand();
        int newAmount = item.getAmount() - amount;
        if (newAmount > 0) {
            item.setAmount(newAmount);
            return;
        }
        player.getInventory().setItemInHand(new ItemStack(Material.AIR));
    }

    public static void deleteAllOfItem(Inventory inv, Map<String, String> nbtString, Material type) {
        for (ItemStack item : inv.getContents()) {
            if (item.getType() != type)
                continue;
            NBTItem nbtItem = new NBTItem(item);
            boolean matches = true;
            for (String key : nbtString.keySet()) {
                if (!nbtItem.getString(key).equals(nbtString.get(key))) {
                    matches = false;
                    break;
                }
            }
            if (!matches)
                continue;
            item.setType(Material.AIR);
        }

    }

    /***
     * @param item the itemstack in question
     * @return true if the item is armor
     */
    public static boolean isArmor(ItemStack item) {
        switch (item.getType()) {
            // Leather
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
            // Chain
            case CHAINMAIL_HELMET:
            case CHAINMAIL_CHESTPLATE:
            case CHAINMAIL_LEGGINGS:
            case CHAINMAIL_BOOTS:
            // Iron
            case IRON_HELMET:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
            // Gold
            case GOLD_HELMET:
            case GOLD_CHESTPLATE:
            case GOLD_LEGGINGS:
            case GOLD_BOOTS:
            // Diamond
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS:
                return true;
            default:
                return false;
        }
    }


}
