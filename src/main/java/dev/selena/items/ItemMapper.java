package dev.selena.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.selena.consts.NBTConsts;
import dev.selena.text.ContentUtils;
import dev.selena.text.MessageUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemMapper {


    public String Horde_Name;
    public String Material_Type;
    public String Material_Color;
    public String Display_Name;
    public List<String> Lore_Lines;
    public boolean Glowing;
    public boolean Usable;
    public Map<String, Integer> Enchants;
    public int Amount;

    public boolean Right_Click_Command_Enabled;
    public String[] Commands;

    public ItemMapper(String entityName, String itemType, String color, String title, List<String> loreLines, boolean glowing, Map<String, Integer> enchants, boolean usable, int amount, boolean command, String[] commands) {
        this.Horde_Name = entityName;
        this.Material_Type = itemType;
        this.Material_Color = color;
        this.Display_Name = title;
        this.Lore_Lines = loreLines;
        this.Glowing = glowing;
        this.Usable = usable;
        this.Enchants = enchants;
        this.Amount = amount;
        this.Right_Click_Command_Enabled = command;
        this.Commands = commands;

    }

    public ItemMapper setAmount(int amount) {
        this.Amount = amount;
        return this;
    }

    public ItemStack createItemStack() {
        ItemStack item = new ItemStack(Material.valueOf(Material_Type));
        NBTItem nbtItem = new NBTItem(item);
        if (Horde_Name != null) {
            nbtItem.setBoolean(NBTConsts.HORDE_ITEM, true);
            nbtItem.setString(NBTConsts.HORDE_NAME, Horde_Name);
        }
        if (Right_Click_Command_Enabled) {
            StringBuilder commands = new StringBuilder();
            boolean first = true;
            for (String command : Commands) {
                if (first) {
                    commands.append(command);
                    first = false;
                }
                else
                    commands.append(NBTConsts.COMMAND_SPLIT).append(command);
            }
            nbtItem.setString(NBTConsts.COMMANDS, commands.toString());
            nbtItem.setBoolean(NBTConsts.COMMANDS_ENABLED, true);
        }

        item = nbtItem.getItem();
        ItemMeta meta = item.getItemMeta();
        if (Glowing && (Enchants == null || Enchants.isEmpty())) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.LUCK, 1, true);
        }

        nbtItem.setBoolean(NBTConsts.USABLE, Usable);

        if (!(Enchants == null || Enchants.isEmpty())) {
            for (String enchant : Enchants.keySet()) {
                meta.addEnchant(Enchantment.getByName(enchant), Enchants.get(enchant), true);
            }
        }
        item.setItemMeta(meta);
        if (Display_Name != null)
            meta.setDisplayName(ContentUtils.color(Display_Name));

        item.setItemMeta(meta);
        List<String> lore = new ArrayList<>();
        if (Lore_Lines != null) {
            Lore_Lines.forEach(line -> {

                String component = ContentUtils.color(line);
                lore.add(component);
            });
            meta.setLore(lore);

            item.setItemMeta(meta);
        }

        if (ItemUtils.isArmor(item) && item.getType().toString().startsWith("LEATHER")) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) item.getItemMeta();
            if (Material_Color != null) {
                Color color = Color.decode(MessageUtils.placeholders(Material_Color).replace("<", "").replace(">", ""));
                org.bukkit.Color mcColor = org.bukkit.Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue());

                leatherArmorMeta.setColor(mcColor);
                item.setItemMeta(leatherArmorMeta);
            }
        }


        item.setAmount(Amount);

        return item;
    }
}
