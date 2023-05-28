package dev.selena.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.selena.consts.NBTConsts;
import dev.selena.text.Placeholders;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler
    public void consumeItem(PlayerInteractEvent event) {
        if (!event.hasItem() || event.getItem() == null)
            return;
        ItemStack item = event.getItem();
        NBTItem nItem = new NBTItem(item);

        if (!nItem.hasCustomNbtData())
            return;
        if (!nItem.getBoolean(NBTConsts.GLITCH_ITEM))
            return;
        if (event.getAction() == Action.LEFT_CLICK_BLOCK)
            return;

        if (nItem.getBoolean(NBTConsts.COMMANDS_ENABLED)) {
            String[] commands = nItem.getString(NBTConsts.COMMANDS).split(NBTConsts.COMMAND_SPLIT);
            for (String command : commands) {
                event.getPlayer().getServer().dispatchCommand(event.getPlayer().getServer().getConsoleSender(), Placeholders.commandPlaceholder(command, event.getPlayer()));
            }
            ItemUtils.deleteItemInHand(event.getPlayer(), 1);
        }
        event.setCancelled(!nItem.getBoolean(NBTConsts.USABLE));
        event.getPlayer().updateInventory();
    }
}
